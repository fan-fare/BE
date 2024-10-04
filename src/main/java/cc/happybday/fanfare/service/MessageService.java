package cc.happybday.fanfare.service;

import cc.happybday.fanfare.common.exception.BusinessException;
import cc.happybday.fanfare.domain.CandleColor;
import cc.happybday.fanfare.domain.Member;
import cc.happybday.fanfare.domain.Message;
import cc.happybday.fanfare.dto.cake.CakeResponseDto;
import cc.happybday.fanfare.dto.message.*;
import cc.happybday.fanfare.repository.MemberRepository;
import cc.happybday.fanfare.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static cc.happybday.fanfare.common.response.ErrorResponseCode.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public Long saveMessage(CreateMessageRequestDto request) {
        Member member = memberRepository.findByUuid(request.getMemberUuid())
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));

        Message message = request.toMessage(member);
        Message savedMessage = messageRepository.save(message);

        return savedMessage.getId();
    }

    public GetMessageResponseDto readMessage(Long messageId) {

        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new BusinessException(MESSAGE_NOT_FOUND));

        if (!Objects.equals(message.getMember().getId(), memberService.getCurrentMember().getId())) {
            throw new BusinessException(FORBIDDEN_ACCESS);
        }

        Long beforeMessageId = messageRepository.findBeforeMessageId(message.getMember().getId(), messageId)
                .orElse(0L);
        Long nextMessageId = messageRepository.findNextMessageId(message.getMember().getId(), messageId)
                .orElse(0L);
        Long totalCount = messageRepository.countAllByMember_Id(message.getMember().getId())
                .orElseThrow(() -> new BusinessException(MESSAGE_NOT_FOUND));
        Long currentCount = messageRepository.findMessagePosition(message.getMember().getId(), messageId)
                .orElseThrow(() -> new BusinessException(MESSAGE_NOT_FOUND));
        return GetMessageResponseDto.toDto(message, beforeMessageId, nextMessageId, totalCount, currentCount);
    }
    public AllMessageResponseListDto readAllMessages(UUID memberUuid, Long start, Long end) {

        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));

        List<Message> messages = messageRepository.findAllByMember_IdOrderByCreatedAtAsc(member.getId());

        Long totalCount = (long) messages.size();

        // 빈 리스트일 때 처리
        if (messages.isEmpty()) {
            return AllMessageResponseListDto.toList(Collections.emptyList(), totalCount);
        }

        if (start == -1)
            start = 0L;
        if (end == -1)
            end = totalCount - 1;

        // start와 end 인덱스가 유효한지 확인 (start는 0 이상, end는 리스트 크기 이하)
        if (start < 0 || end > totalCount || start > end) {
            throw new BusinessException(INVALID_MESSAGE_INDEX);
        }

        // start 인덱스부터 end 인덱스까지의 하위 리스트 생성
        List<Message> subMessages = messages.subList(start.intValue(), end.intValue() + 1);

        List<MessageResponseDto> messageResponseDtos = subMessages.stream()
                .map(MessageResponseDto::toDto)
                .toList();

        return AllMessageResponseListDto.toList(messageResponseDtos, totalCount);
    }

    public String deleteMessage(Long messageId) {

        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new BusinessException(MESSAGE_NOT_FOUND));

        if (!Objects.equals(message.getMember().getId(), memberService.getCurrentMember().getId())) {
            throw new BusinessException(FORBIDDEN_ACCESS);
        }

        messageRepository.deleteById(message.getId());

        return "메세지 삭제에 성공했습니다.";
    }

    public List<Long> getMessageIdList(Long memberId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Message> messagePage = messageRepository.findAllByMember_IdOrderByCreatedAtAsc(memberId, pageable);

        return messagePage.stream()
                .map(Message::getId)
                .toList();
    }

    public List<String> getMessageSenderNicknameList(Long memberId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Message> messagePage = messageRepository.findAllByMember_IdOrderByCreatedAtAsc(memberId, pageable);

        return messagePage.stream()
                .map(Message::getNickname)
                .toList();
    }

    public List<CandleColor> getCandleColorsList(Long memberId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Message> messagePage = messageRepository.findAllByMember_IdOrderByCreatedAtAsc(memberId, pageable);

        return messagePage.stream()
                .map(Message::getCandleColor)
                .toList();
    }

    public Long getMessageTotalCount(Long memberId){
        return messageRepository.countAllByMember_Id(memberId)
                .orElseThrow(() -> new BusinessException(MESSAGE_NOT_FOUND));
    }

    public CakeResponseDto getCake(Member member) {
        List<CakeMessageResponseDto> messageDtos = messageRepository.findAllByMember_IdOrderByCreatedAtAsc(member.getId()).stream()
                .map(message -> CakeMessageResponseDto.builder()
                        .messageId(message.getId())
                        .senderNickname(message.getNickname())
                        .candleColor(message.getCandleColor())
                        .build())
                .collect(Collectors.toList());

        return CakeResponseDto.builder()
                .nickname(member.getNickname())
                .birthDay(member.getBirthDay())
                .messages(messageDtos)
                .build();
    }


}
