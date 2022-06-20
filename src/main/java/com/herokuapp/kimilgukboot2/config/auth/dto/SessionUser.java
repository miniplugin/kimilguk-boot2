package com.herokuapp.kimilgukboot2.config.auth.dto;

import java.io.Serializable;

import com.herokuapp.kimilgukboot2.domain.users.Users;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
	private String name;//로그인 아이디를 세션으로 사용
	private String role;//로그인 권한(admin,user,quest)을 세션으로 사용
	//네이버 에서 받는 값때문에 2개 추가(아래)
	private String email;
	private String picture;
	
	public SessionUser(String name, String role) {
		this.name = name;
		this.role = role;
	}

	public SessionUser(Users user) {
		this.name = user.getName();
		this.role = user.getRoleKey();
		this.email = user.getEmail();
		this.picture = user.getPicture();
	}	
}
