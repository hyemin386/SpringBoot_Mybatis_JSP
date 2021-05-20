package com.hm.j1.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class MemberVO {

	private String userName;
	//최소 4글자 이상이면 통과
	@Size(min=4)
	private String password;
	
	@NotEmpty(message="넌 이름이 뭐닝") //null이 아니여야함
	private String name;
	
	@Email
	@NotEmpty
	private String email;
	private String phone;
	
	//0 이상 200이하
	@Range(min=0, max=200)
	private Integer age;
	
}
