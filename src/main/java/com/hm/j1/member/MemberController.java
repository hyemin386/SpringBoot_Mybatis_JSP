package com.hm.j1.member;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/**")
public class MemberController {

	@GetMapping("join")
	public void join(Model model) throws Exception {
		model.addAttribute("memberVO", new MemberVO());
	}
	
	@PostMapping("join")
	public String join(@Valid MemberVO memberVO, Errors bindingResult) throws Exception { //Valid 검증하겠다는 뜻
		//bindingResult에 검증된 결과를 담음
		if(bindingResult.hasErrors()) { //검증 시 에러가 발생하면 join으로
			return "member/join";
		} 
		return "redirect:/";
	}
}
