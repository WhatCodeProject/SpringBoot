package whatcode.study.whatcode.domain.chat.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whatcode.study.whatcode.domain.member.member.Member;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long>{}
