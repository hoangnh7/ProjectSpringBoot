package com.example.demo.controller.admin;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.model.request.CreatePostReq;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.PostService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManagePostController {
    @Autowired
    private PostService postService;
    @GetMapping("/admin/posts")
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
        model.addAttribute("posts",result.getContent());
        System.out.println(result.getContent().get(1).getPublishedAt());
        System.out.println(result.getTotalPages());
        return "admin/blog/list";

    }
    @GetMapping("/admin/posts/{id}")
    public String getDetailPost(Model model, @PathVariable Long id) {
        Post rs = postService.getPostById(id);
        List<Post> lastedPost = postService.getLastedPost();
        model.addAttribute("post",rs);
        model.addAttribute("lastedPost",lastedPost);
        return "admin/blog/detail";
    }
    @GetMapping("/admin/posts/create")
    public String getCreatePage() {
//        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
//        Post rs = postService.createPost(req,user);

        return "admin/blog/create";
    }
    @PostMapping("api/admin/posts")
    public ResponseEntity<?> getPostCreatePage(Model model, @RequestBody CreatePostReq req) {
        // Get list image of user
        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Post rs = postService.createPost(req,user);
//        model.addAttribute("images", images);
        return ResponseEntity.ok(rs);
    }
    @DeleteMapping("api/admin/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable long id) {
        postService.deletePost(id);
        return ResponseEntity.ok(123);
    }
}

