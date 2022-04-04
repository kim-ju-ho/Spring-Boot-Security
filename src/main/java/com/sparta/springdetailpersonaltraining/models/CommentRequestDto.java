package com.sparta.springdetailpersonaltraining.models;


import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long parentsId;
    private String comment;
    private String name;

}
