package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.dto.PostDto;
import com.example.demo.model.request.CreatePostReq;
import com.example.demo.repository.PostRepository;
import com.github.slugify.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Component
public class    PostServiceImpl implements PostService{
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

    @Override
    public Post createPost(CreatePostReq req,User user) {
        Post rs = new Post();
        rs.setContent(req.getContent());
        rs.setTitle(req.getTitle());
        Slugify slugify= new Slugify();
        rs.setSlug(slugify.slugify(req.getTitle()));
        if(req.getStatus()==1){
            if (req.getDescription().isEmpty()) {
                throw new BadRequestException("Để công khai bài viết vui lòng nhập mô tả");
            }
//            if (req.getImage().isEmpty()) {
//                throw new BadRequestException("Vui lòng chọn ảnh cho bài viết trước khi công khai");
//            }
            rs.setPublishedAt(new Timestamp(System.currentTimeMillis()));
        }else {
            throw new BadRequestException("Trạng thái bài viết không hợp lệ");
        }
        rs.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        rs.setCreatedBy(user);
        rs.setDescription(req.getDescription());
//        rs.setThumbnail(req.getImage());
        rs.setStatus(req.getStatus());
        postRepository.save(rs);
        return rs;
    }

    @Override
    public void deletePost(long id) {
        Optional<Post> post = postRepository.findById(id);
        Post rs = post.get();
        postRepository.delete(rs);
    }
}
