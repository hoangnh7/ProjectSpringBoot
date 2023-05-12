package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.model.dto.PostDto;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostServiceImpl implements PostService{
    @Autowired
    PostRepository postRepository;
    @Override
    public List<Post> getListPost() {
        List<Post> posts = postRepository.findAllByOrderByPublishedAtDesc();
        return posts;
    }
}
