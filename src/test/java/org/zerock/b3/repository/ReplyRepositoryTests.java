package org.zerock.b3.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b3.entity.Board;
import org.zerock.b3.entity.Reply;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void testInsert(){

        Board board = Board.builder().bno(100).build();

        for (int i = 0; i < 100; i++) {
            Reply reply = Reply.builder()
                    .board(board)
                    .replyText(i+" 댓글")
                    .replyer("replyer0"+i)
                    .build();
            replyRepository.save(reply);
        }//end for

    }

}
