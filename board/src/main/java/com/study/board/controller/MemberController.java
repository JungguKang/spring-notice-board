package com.study.board.controller;

import com.study.board.entity.Member;
import com.study.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String memberLogin(){
        return "loginPage";
    }


    @PostMapping("/login/checking")
    public String memberLoginCheck(String userId, String password, Model model){
        if(memberService.login(userId, password)){
            model.addAttribute("message", "로그인 성공");
            model.addAttribute("searchUrl", "/board/list");
        }else{
            model.addAttribute("message", "로그인 실패");
            model.addAttribute("searchUrl", "/login");
        }

        return "message";
    }

    @GetMapping("/register")
    public String memberRegisterForm(){
        return "registerPage";
    }

    @PostMapping("/login/register")
    public String memberRegister(Member member, Model model){
        try{
            memberService.register(member);
        } catch(Exception e){
            model.addAttribute("message", "중복아이디입니다.");
            model.addAttribute("searchUrl", "/register");
            return "message";
        }
        model.addAttribute("message", "회원가입 완료!!! 로그인해주세요.");
        model.addAttribute("searchUrl", "/login");
        return "message";
    }

}
