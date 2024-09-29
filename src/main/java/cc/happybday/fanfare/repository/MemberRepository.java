package cc.happybday.fanfare.repository;

import cc.happybday.fanfare.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsById(Long id);
    Optional<Member> findById(Long id);
    Optional<Member> findByUuid(UUID uuid);
    @Query("SELECT m.id FROM Member m WHERE m.uuid = :uuid")
    Optional<Long> findIdByUuid(@Param("uuid") UUID uuid);  // UUID로 ID 조회
    @Query("SELECT m.uuid FROM Member m WHERE m.id = :id")
    Optional<UUID> findUuidById(@Param("id") Long id);  // ID로 UUID 조회

    Optional<Member> findByUsername(String username);
    @Query("SELECT m FROM Member m WHERE MONTH(m.birthDay) = :month AND DAY(m.birthDay) = :day")
    List<Member> findByBirthDay(@Param("month") int month, @Param("day") int day);
    void deleteById(Long id);

    List<Member> findAllByUuidIsNull();
}
