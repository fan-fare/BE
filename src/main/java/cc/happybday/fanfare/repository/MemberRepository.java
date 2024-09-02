package cc.happybday.fanfare.repository;

import cc.happybday.fanfare.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsById(Long id);
    Optional<Member> findById(Long id);
    Optional<Member> findByUsername(String username);

    List<Member> findByBirthDay(LocalDate birthDay);
    void deleteById(Long id);

}
