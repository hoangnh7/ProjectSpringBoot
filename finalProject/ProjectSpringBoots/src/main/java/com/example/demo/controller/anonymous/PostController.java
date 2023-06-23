package com.example.demo.controller.anonymous;

import com.example.demo.entity.Post;
import com.example.demo.model.dto.UserDto;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller

public class PostController {

    @Autowired
    private PostService postService;
    @GetMapping("/tin-tuc")
    public String getBlogPage(Model model, @RequestParam(required = false) Integer page) {
        if (page==null){
            page = 0;
        } else  {
            page--;
            if (page < 0) {
                page = 0;
            }
        }
        Page<Post> result = postService.getListPost(page);
        model.addAttribute("totalPage",result.getTotalPages());
        model.addAttribute("currentPage",++page);
        model.addAttribute("listPost",result.getContent());
        System.out.println(result.getContent().get(1).getPublishedAt());
        System.out.println(result.getTotalPages());
        return "shop/news";

    }
    @GetMapping("/tin-tuc/{slug}/{id}")
    public String getDetailPost(Model model, @PathVariable Long id) {
        Post rs = postService.getPostById(id);
        List<Post> lastedPost = postService.getLastedPost();
        model.addAttribute("post",rs);
        model.addAttribute("lastedPost",lastedPost);
        return "shop/newsDetail";
    }


//    public ResponseEntity<?> getListPost(){
//        Page<Post> result = postService.getListPost(1,0);
//
//        return ResponseEntity.ok(result);
////        System.out.println(result.getContent(0));
//    }
}
