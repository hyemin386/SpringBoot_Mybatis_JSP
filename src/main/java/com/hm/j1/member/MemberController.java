package com.hm.j1.member;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/**")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("error")
	public String error() {
		return "error/error";
	}
	
	//회원가입
	@GetMapping("join")      //model에 memberVO객체를 넣는것이랑 똑같은 역할
	public String memberJoin(@ModelAttribute MemberVO memberVO) throws Exception {
		return "member/memberJoin";
	}
	
	@PostMapping("join")
	public String setJoin(@Validated MemberVO memberVO, Errors errors, MultipartFile avatar)throws Exception{
		
		/* 기본검증
		    if(errors.hasErrors()) {
			return "member/memberJoin";
		} */
		
		if(memberService.memberError(memberVO, errors)) {
			return "member/memberJoin";
		}
		int result = memberService.memberJoin(memberVO, avatar);
		
		return "redirect:../";
	}
	
	//로그인
	@GetMapping("login")      
	public String memberLogin() throws Exception {
		return "member/memberLogin";
	}
	
	@PostMapping("memberLogin")
	public String getLogin(HttpServletRequest request) throws Exception {
		System.out.println("Message: "+request.getAttribute("message"));
		return "member/memberLogin";
	}
	
	//로그인 실패시 처리
	@GetMapping("loginFail")
	public String loginFail() throws Exception {
		System.out.println("로그인 실패");
		
		return "redirect:/member/login";
	}
	
	@GetMapping("memberLoginResult")
	public String memberLoginResult(HttpSession session) throws Exception {
		//session의 속성명들 꺼내오기
		Enumeration<String> en = session.getAttributeNames();
		
		while(en.hasMoreElements()) { 
			System.out.println("Attribute Name: "+en.nextElement());
		}
		
		//로그인시 session의 속성명 : SPRING_SECURITY_CONTEXT
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		System.out.println(obj);
		
		SecurityContextImpl sc = (SecurityContextImpl)obj; //obj 형변환
		Authentication auth = sc.getAuthentication();
		
		System.out.println("Name: "+auth.getName());
		System.out.println("Detail: "+auth.getDetails());
		System.out.println("Principal+ "+auth.getPrincipal());
		System.out.println("Authorities: "+auth.getAuthorities());

		System.out.println("로그인 성공");
		return "redirect:/";
	}
	
	/*@PostMapping("login")
	public String memberLogin(MemberVO memberVO, HttpSession session) throws Exception {
		memberVO = memberService.memberLogin(memberVO);
		session.setAttribute("member", memberVO);
		System.out.println(memberVO.getName()+" 로그인 성공");
		return "redirect:../";
	} */
	
	//로그아웃
	@GetMapping("logout")
	public String memberLogout(HttpSession session) throws Exception {
		session.invalidate();
		System.out.println("로그아웃 성공");
		return "redirect:../";
	}
	
	//마이페이지
	@GetMapping("page")
	public String memberPage () throws Exception {
		return "member/memberPage";
	}
	
}