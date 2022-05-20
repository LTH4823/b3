package org.zerock.b3.service;

import org.modelmapper.ModelMapper;
import org.zerock.b3.dto.BoardDTO;
import org.zerock.b3.dto.PageRequestDTO;
import org.zerock.b3.dto.PageResponseDTO;

public interface BoardService {

    Integer register(BoardDTO boardDTO);

    BoardDTO readOne(Integer bno);

    void modify(BoardDTO boardDTO);

    void remove(Integer bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

}
