package cc.happybday.fanfare.service;

import cc.happybday.fanfare.common.exception.BusinessException;
import cc.happybday.fanfare.domain.Member;
import cc.happybday.fanfare.domain.Message;
import cc.happybday.fanfare.dto.message.CreateMessageRequestDto;
import cc.happybday.fanfare.repository.MemberRepository;
import cc.happybday.fanfare.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static cc.happybday.fanfare.common.response.ErrorResponseCode.MEMBER_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;

    public Long saveMessage(CreateMessageRequestDto request) {
        Long memberId = request.getMemberId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));

        Message message = request.toMessage(member);
        Message savedMessage = messageRepository.save(message);

        return savedMessage.getId();
    }

}
