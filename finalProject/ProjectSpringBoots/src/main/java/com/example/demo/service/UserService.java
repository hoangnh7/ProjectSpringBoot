package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.CreateUserReq;
import com.example.demo.model.request.UpdateUserReq;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User createUser(CreateUserReq req);
    public User updateUser(UpdateUserReq req, String email);
}
