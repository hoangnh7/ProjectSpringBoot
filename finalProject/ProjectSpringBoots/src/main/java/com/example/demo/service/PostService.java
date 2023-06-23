package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.model.dto.PostDto;
import com.example.demo.model.request.CreatePostReq;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
//    public Page<Post> getListPost(int page);
    public Page<Post> getListPost(int page);
    public Post getPostById(long id);
    public List<Post> getLastedPost();
    public Post createPost(CreatePostReq req, User user);
    public void deletePost(long id);
}
