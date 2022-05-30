package org.zerock.b3.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_bimage")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    private String uuid;

    private String fileName;

    private boolean img;

    private int ord;

    public void fixOrd(int ord){
        this.ord = ord;
    }
}
