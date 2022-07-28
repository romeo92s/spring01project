package com.sparta.spring01project.service;

import com.sparta.spring01project.domain.Post;
import com.sparta.spring01project.domain.PostRepository;
import com.sparta.spring01project.domain.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public List<PostsResponseDto> findAllByOrderByModifiedAtDesc() {
        List<Post> postList = new ArrayList<>(postRepository.findAllByOrderByModifiedAtDesc());
        List<PostsResponseDto> postsResponseDtoList = new ArrayList<>();
        for (Post post : postList) {
            postsResponseDtoList.add(new PostsResponseDto(post));
        }
        return postsResponseDtoList;
    }

    @Transactional
    public PostResponseDto findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        return new PostResponseDto(post);
    }

    @Transactional
    public ResponseEntity<String> createPost(@RequestBody PostRequestDto postRequestDto){
        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return new ResponseEntity<>("작성되었습니다", HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<String> updatePost(Long id,@RequestBody PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다"));
        post.update(postRequestDto);
        return new ResponseEntity<>("수정되었습니다", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deletePost(Long id){
        postRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    public ResponseEntity<String> checkPassword(Long id, String password) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다"));
        if (!post.getPassword().equals(password)) {
            return new ResponseEntity<>("비밀번호가 틀렸습니다", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
