package com.example.demo.controller.anonymous;

import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.dto.OrderInfoDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.model.request.CreateUserReq;
import com.example.demo.model.request.LoginReq;
import com.example.demo.model.request.UpdateUserReq;
import com.example.demo.repository.OrderRepository;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;

import static com.example.demo.config.Constant.MAX_AGE_COOKIE;


@Controller
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private OrderService orderService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;
    @PostMapping("/api/register")
    public ResponseEntity<?> register(@Valid @RequestBody CreateUserReq req, HttpServletResponse response) {
        // Create user
        User result = userService.createUser(req);

        // Gen token
        UserDetails principal = new CustomUserDetails(result);
        String token = jwtTokenUtil.generateToken(principal);

        // Add token to cookie to login
        Cookie cookie = new Cookie("JWT_TOKEN",token);
        cookie.setMaxAge(MAX_AGE_COOKIE);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(UserMapper.toUserDto(result));
    }
    @PostMapping("/api/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginReq req, HttpServletResponse response) {
        // Authenticate
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getEmail(),
                            req.getPassword()
                    )
            );

            // Gen token
            String token = jwtTokenUtil.generateToken((CustomUserDetails) authentication.getPrincipal());

            // Add token to cookie to login
            Cookie cookie = new Cookie("JWT_TOKEN", token);
            cookie.setMaxAge(MAX_AGE_COOKIE);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseEntity.ok(UserMapper.toUserDto(((CustomUserDetails) authentication.getPrincipal()).getUser()));
        } catch (Exception ex) {
            throw new BadRequestException("Email hoặc mật khẩu không chính xác");
        }
    }


//    @PostMapping("/api/register")
////    public ResponseEntity<?> register(@Valid @RequestBody CreateUserReq req, HttpServletResponse response) {
////        // Create user
////        User result = userService.createUser(req);
////
////        // Gen token
////        UserDetails principal = new CustomUserDetails(result);
////        String token = jwtTokenUtil.generateToken(principal);
////
////        // Add token to cookie to login
////        Cookie cookie = new Cookie("JWT_TOKEN",token);
////        cookie.setMaxAge(MAX_AGE_COOKIE);
////        cookie.setPath("/");
////        response.addCookie(cookie);
////
////        return ResponseEntity.ok(UserMapper.toUserDto(result));
////    }
////    @PostMapping("/api/login")
//    public ResponseEntity<?> login(@RequestBody CreateUserReq req, HttpServletResponse response) {
//        // Create user
//        UserDto result = userService.createUser(req);
//        System.out.println(result);
//
//        return ResponseEntity.ok("Thành công");
//    }
    @GetMapping("/tai-khoan")
    public String getAccount(Model model) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        AuthenticateReq req;
//        model.addAttribute("name",userDetails.getUser().getName());
//        model.addAttribute("email",userDetails.getUsername());
//        model.addAttribute("phone",userDetails.getUser().getPhone());
////        model.addAttribute("")
        return "account/account";
    }
    @PostMapping("/api/update-profile")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserReq req){
        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        user = userService.updateUser(req,user.getEmail());
        return ResponseEntity.ok(123);
    }
    @Autowired
    private OrderRepository orderRepository;
    @GetMapping("/tai-khoan/lich-su-giao-dich")
    public String getOrderHistory(Model model){
        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        System.out.println(user);
        List<OrderInfoDto> orders = orderService.getListOrderById(user.getId());


        model.addAttribute("orders",orders);
        return "shop/order_history";
    }
}
