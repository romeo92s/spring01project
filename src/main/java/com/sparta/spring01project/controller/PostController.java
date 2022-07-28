package com.sparta.spring01project.controller;

import com.sparta.spring01project.domain.Post;
import com.sparta.spring01project.domain.PostRequestDto;
import com.sparta.spring01project.domain.Post;
import com.sparta.spring01project.domain.PostRepository;
import com.sparta.spring01project.domain.PostRequestDto;
import com.sparta.spring01project.service.PostResponseDto;
import com.sparta.spring01project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/api/posts/{id}")  //게시글 전체조회
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.findById(id);
    }

    @PostMapping("/api/posts")//   게시글생성
    public Post createdpost(@RequestBody PostRequestDto requestDto){
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }
    @GetMapping("/api/posts")    //  게시글 조회
    public List<Post> readPost(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }
    @PutMapping("/api/posts/{id}")   // 게시글 수정
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        postService.updatePost(id,requestDto);
        return id;
    }

    @DeleteMapping("/api/posts/{id}")   //  게시글 삭제
    public Long deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        return id;
    }
}
