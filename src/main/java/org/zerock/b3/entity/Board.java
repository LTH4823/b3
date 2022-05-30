package org.zerock.b3.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_board")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString(exclude = "boardImages")
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bno;

    @Column(length = 200, nullable = false)
    private String title;

    private String content;

    private String writer;

//    @CreationTimestamp
//    private LocalDateTime regDate;
//
//    @LastModifiedDate
//    private LocalDateTime updateDate;

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content){
        this.content = content;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="board")
    @Builder.Default
    private Set<BoardImage> boardImages = new HashSet<>();

    public void addImage(BoardImage boardImage) {

        boardImage.fixOrd(boardImages.size());
        boardImages.add(boardImage);
    }


}
