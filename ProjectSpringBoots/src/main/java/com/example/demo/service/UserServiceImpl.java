package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.model.request.CreateUserReq;
import com.example.demo.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto createUser(CreateUserReq req) {
        User rs = userRepository.findByEmail(req.getEmail());
        if (rs != null) {
//            throw new NotFoundException("email is already exist");
        }
        // Convert CreateUserReq -> User
        User user = new User();
//        user.setId(users.size()+1);
        user.setPhone(req.getPhone());
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        // Mã hóa mật khẩu sử dụng BCrypt
        user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));

        // Thêm user
//        users.add(user);
        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }
}
