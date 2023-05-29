package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.model.dto.PostDto;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PostServiceImpl implements PostService{
    @Autowired
    PostRepository postRepository;
    @Override
    public Page<Post> getListPost(int page) {
        Page<Post> result = postRepository.findAllByStatus(1,PageRequest.of(page, 6, Sort.by("publishedAt").descending()));
//        List<Post> result = postRepository.findAllByStatus(status);
//        Page<Post> page = postRepository.findAll(PageRequest.of(0, 5, Sort.by("publishedAt").descending()));

        return result;
    }

    @Override
    public Post getPostById(long id) {
        Post rs = postRepository.getById(id);
        return rs;
    }

    @Override
    public List<Post> getLastedPost() {
        List<Post> rs = postRepository.getLastedPostById(1,5);
        return rs;
    }
}
