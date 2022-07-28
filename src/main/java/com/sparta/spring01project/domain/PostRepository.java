package com.sparta.spring01project.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
}
///   findAllByOrderByModifiedAtDesc  >> 전부 찾아서 수정된날짜 기준 내림차순으로 정렬해줘,123 >오름차순 987>내림차순(Desc)