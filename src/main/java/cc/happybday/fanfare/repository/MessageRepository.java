package cc.happybday.fanfare.repository;

import cc.happybday.fanfare.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findById(Long id);
    Message save(Message message);

    @Query("SELECT MAX(m.id) FROM Message m WHERE m.member.id = :memberId AND m.id < :messageId")
    Optional<Long> findBeforeMessageId(@Param("memberId") Long memberId, @Param("messageId") Long messageId);

    @Query("SELECT MIN(m.id) FROM Message m WHERE m.member.id = :memberId AND m.id > :messageId")
    Optional<Long> findNextMessageId(@Param("memberId") Long memberId, @Param("messageId") Long messageId);

}
