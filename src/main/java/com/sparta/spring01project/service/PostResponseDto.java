package com.sparta.spring01project.service;

import com.sparta.spring01project.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private final String title;
    private final String contents;

    private final String password;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.password = post.getPassword();
        this.modifiedAt = post.getModifiedAt();
        this.createdAt = post.getCreatedAt();

    }
}

