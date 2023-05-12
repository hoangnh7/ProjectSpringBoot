package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.model.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    public List<Post> getListPost();

}
