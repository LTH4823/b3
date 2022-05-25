package org.zerock.b3.service;

import org.zerock.b3.dto.PageRequestDTO;
import org.zerock.b3.dto.PageResponseDTO;
import org.zerock.b3.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    ReplyDTO read(Long rno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    PageResponseDTO<ReplyDTO> getListOfBoard(Integer bno, PageRequestDTO pageRequestDTO);

}
