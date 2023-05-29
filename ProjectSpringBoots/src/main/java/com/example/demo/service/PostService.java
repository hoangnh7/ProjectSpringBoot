package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.model.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
//    public Page<Post> getListPost(int page);
    public Page<Post> getListPost(int page);
    public Post getPostById(long id);
    public List<Post> getLastedPost();
}
