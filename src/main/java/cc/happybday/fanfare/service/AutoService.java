package cc.happybday.fanfare.service;

import cc.happybday.fanfare.domain.Member;
import cc.happybday.fanfare.repository.MemberRepository;
import cc.happybday.fanfare.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AutoService {

    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;


    @Scheduled(cron = "0 0 0 * * *")
    public void autoDelete() throws Exception {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
        List<Member> members = memberRepository.findByBirthDay(sevenDaysAgo);
        if (members.isEmpty()) {
            log.info("7일전 날짜에 생일인 회원이 없습니다.");
        }
        for (Member member : members) {
            messageRepository.deleteAllByMember(member);
            memberRepository.deleteById(member.getId());
        }
    }

}
