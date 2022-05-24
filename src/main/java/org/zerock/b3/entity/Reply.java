package org.zerock.b3.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_reply")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String replyText;

    private String replyer;

    @ManyToOne
    private Board board;



}
