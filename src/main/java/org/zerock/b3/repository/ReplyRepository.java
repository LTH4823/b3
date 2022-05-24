package org.zerock.b3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.b3.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
