package com.herokuapp.kimilgukboot2.config.auth.dto;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
	private String name;//로그인 아이디를 세션으로 사용
	private String role;//로그인 권한(admin,user,quest)을 세션으로 사용
	
	public SessionUser(String name, String role) {
		this.name = name;
		this.role = role;
	}	
}
