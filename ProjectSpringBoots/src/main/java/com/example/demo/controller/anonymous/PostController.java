package com.example.demo.controller.anonymous;

import com.example.demo.entity.Post;
import com.example.demo.model.dto.UserDto;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller

public class PostController {
    @Autowired
    private PostRepository postRepository;
    @GetMapping("/tin-tuc")
    public ResponseEntity<?> getListPost(){
        List<Post> result = postRepository.findAllByOrderByPublishedAtDesc();
        return ResponseEntity.ok(result);
    }
}
