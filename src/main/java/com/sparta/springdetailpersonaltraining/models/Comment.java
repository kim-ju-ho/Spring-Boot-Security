package com.sparta.springdetailpersonaltraining.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment  extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private Long parentsId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String comment;

    public Comment(CommentRequestDto commentRequestDto){
        this.parentsId = commentRequestDto.getParentsId();
        this.name = commentRequestDto.getName();
        this.comment = commentRequestDto.getComment();

    }

    public void update(CommentRequestDto commentRequestDto) {
//        this.parentsId = commentRequestDto.getParentsId();
//        this.name      = commentRequestDto.getName();
        this.comment   = commentRequestDto.getComment();
    }
}
