package cc.happybday.fanfare.repository;

import cc.happybday.fanfare.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsById(Long id);
    Optional<Member> findById(Long id);
    Optional<Member> findByUsername(String username);

    @Query("SELECT m FROM Member m WHERE MONTH(m.birthDay) = :month AND DAY(m.birthDay) = :day")
    List<Member> findByBirthDay(@Param("month") int month, @Param("day") int day);

    void deleteById(Long id);

}
