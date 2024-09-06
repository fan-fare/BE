package cc.happybday.fanfare.service;

import cc.happybday.fanfare.common.exception.BusinessException;
import cc.happybday.fanfare.domain.Cake;
import cc.happybday.fanfare.domain.CandleColor;
import cc.happybday.fanfare.domain.Member;
import cc.happybday.fanfare.domain.Message;
import cc.happybday.fanfare.dto.cake.CakeResponseDto;
import cc.happybday.fanfare.dto.message.CreateMessageRequestDto;
import cc.happybday.fanfare.dto.message.GetMessageResponseDto;
import cc.happybday.fanfare.repository.MemberRepository;
import cc.happybday.fanfare.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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
        Long memberId = request.getMemberId();
        Member member = memberRepository.findById(memberId)
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

    public CakeResponseDto getCake(Member member, int page, int size){
        Long totalMessageCount = getMessageTotalCount(member.getId());
        Long totalCakeCount = (long) Math.ceil((double) totalMessageCount / size);
        List<Long> messageIdList = getMessageIdList(member.getId(), page, size);
        List<String> messageSenderNicknameList = getMessageSenderNicknameList(member.getId(), page, size);
        List<CandleColor> candleColorsList = getCandleColorsList(member.getId(), page, size);
        return CakeResponseDto.builder()
                .totalCakeCount(totalCakeCount)
                .nickname(member.getNickname())
                .birthDay(member.getBirthDay())
                .totalMessageCount(totalMessageCount)
                .messageIdList(messageIdList)
                .messageSenderNicknameList(messageSenderNicknameList)
                .candleColorsList(candleColorsList)
                .build();
    }

}
