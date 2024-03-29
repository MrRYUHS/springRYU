package com.example.springcore.controller;

import com.example.springcore.dto.SignupRequestDto;
import com.example.springcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login(){
        return "login";
    }
    @GetMapping("/user/login/error")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        return "login";
    }
    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(){
        return "signup";
    }
    @GetMapping("/user/forbidden")
    public String forbidden() {
        return "forbidden";
    }
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(String code){
        userService.kakaoLogin(code);

        return "redirect:/";
    }
    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto){
        userService.registerUser(requestDto);
        return "redirect:/";
    }
}
