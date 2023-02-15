package com.humingbird.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.humingbird.dto.MemberFormDto;
import com.humingbird.entity.Member;
import com.humingbird.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class memberController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	//회원가입 화면
	@GetMapping(value = "/new")
	public String memberForm(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "memberForm/memberForm";
	}
	//회원가입누를때
	@PostMapping(value = "/new")
	public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "memberForm/memberForm";
		}
		
		try {
			Member member = Member.createMember(memberFormDto, passwordEncoder);
			memberService.saveMember(member);
			
		}catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "memberForm/memberForm";
		}
		
		return "redirect:/";
	}
	
	//로그인 화면 
	@GetMapping(value = "/login")
	public String loginMember() {
		
		
		
		return "main";
	}
	
	@GetMapping(value = "/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
		return "main";
	}
}
