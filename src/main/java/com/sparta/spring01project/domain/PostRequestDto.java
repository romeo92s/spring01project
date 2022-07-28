package com.sparta.spring01project.domain;


import lombok.Getter;


@Getter
public class PostRequestDto {

    private String username;
    private String contents;
    private String title;
    private String password;


    //private > 생성시 문제가 생기니 @Getter 만들기
}
