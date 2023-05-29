package com.example.demo.controller.anonymous;

import com.example.demo.entity.User;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.AuthenticateReq;
import com.example.demo.model.request.CreateUserReq;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.security.EmbeddedServletContainerCustomizer;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.service.UserService;
//import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.net.URLEncoder;

import static com.example.demo.config.Constant.MAX_AGE_COOKIE;


@Controller
public class UserController {
    private Boolean status= false;

    @Autowired
    private UserService userService;

    @PostMapping("/api/register")

    public ResponseEntity<?> login(@RequestBody CreateUserReq req, HttpServletResponse response) {
        // Create user
        UserDto result = userService.createUser(req);
        System.out.println(result);

        return ResponseEntity.ok("Thành công");
    }
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private CustomUserDetails userDetails = new CustomUserDetails();

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticateReq req, HttpServletResponse response) {
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );
        System.out.println(authentication.isAuthenticated());
        status= authentication.isAuthenticated();

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
        System.out.println(token);
        // Add token to cookie to login
//        Cookie cookie = new Cookie( "JWT_TOKEN", URLEncoder.encode( token, "UTF-8" ) );

        Cookie cookie = new Cookie("JWT_TOKEN", token);
        cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setPath("/");
//        response.addCookie(cookie);
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDetails = user;
        System.out.println(user.getUsername());
        return ResponseEntity.ok("đăng nhập thành công");
        // Gen token

//        return ResponseEntity.ok(token);
    }
    @GetMapping("/tai-khoan")
    public String getAccount(Model model) {
//        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        AuthenticateReq req;
        model.addAttribute("name",userDetails.getUser().getName());
        model.addAttribute("email",userDetails.getUsername());
        model.addAttribute("phone",userDetails.getUser().getPhone());
//        model.addAttribute("")
        return "account/account";
    }
}
