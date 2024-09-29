package cc.happybday.fanfare.service;

import cc.happybday.fanfare.domain.Member;
import cc.happybday.fanfare.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMigrationService {

    private final MemberRepository memberRepository;
    private final EntityManager entityManager;  // EntityManager 주입

    // UUID가 없는 모든 멤버에 대해 UUID를 생성 및 업데이트

    public void migrateMembersWithUUID() {
        List<Member> membersWithoutUUID = memberRepository.findAllByUuidIsNull();

        for (Member member : membersWithoutUUID) {
            member.setUuid(UUID.randomUUID());
            entityManager.persist(member);  // 영속성 컨텍스트에 추가
        }
        entityManager.flush();  // 변경 사항을 즉시 DB에 반영
    }
}

