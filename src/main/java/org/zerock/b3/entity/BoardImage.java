package org.zerock.b3.entity;

import lombok.*;

import javax.persistence.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class BoardImage {

    private String fileLink;

    private int ord;

    public void fixOrd(int ord){
        this.ord = ord;
    }

}