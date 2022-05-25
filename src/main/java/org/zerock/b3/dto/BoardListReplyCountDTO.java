package org.zerock.b3.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListReplyCountDTO {

    private Integer bno;
    private String title;
    private String writer;
    private LocalDateTime regDate;
    private long replyCount;

}
