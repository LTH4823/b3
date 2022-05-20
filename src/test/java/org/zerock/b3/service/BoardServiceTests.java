package org.zerock.b3.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b3.dto.BoardDTO;

@Log4j2
@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testResgiter(){
        BoardDTO boardDTO = BoardDTO.builder()
                .title("Test")
                .content("Test Content")
                .writer("user00")
                .build();

        Integer bno = boardService.register(boardDTO);

        log.info("=================");
        log.info(bno);
    }
}
