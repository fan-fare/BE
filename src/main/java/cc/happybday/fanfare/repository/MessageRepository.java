package cc.happybday.fanfare.repository;

import cc.happybday.fanfare.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findById(Long id);
    Message save(Message message);

}
