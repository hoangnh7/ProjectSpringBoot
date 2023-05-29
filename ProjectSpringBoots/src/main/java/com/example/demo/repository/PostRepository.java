package com.example.demo.repository;

import com.example.demo.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    public List<Post> findAllByOrderByPublishedAtDesc();
    public Page<Post> findAllByStatus(int status, Pageable pageable);
    @Query(value = "select * from post  where status= ?1  ORDER BY published_at DESC LIMIT ?2", nativeQuery = true)
    public List<Post> getLastedPostById(int status, int limit);
//public List<Post> findAllByStatus(int status, Pageable pageable);
//    public List<Post> findAllByStatus(int status);


}
