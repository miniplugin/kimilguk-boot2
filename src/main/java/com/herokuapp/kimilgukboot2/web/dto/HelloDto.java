package com.herokuapp.kimilgukboot2.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor //파라미터를 가진 생성자를 자동으로 만들어주는 기능
public class HelloDto {
	//멤버변수
	private final String name;
	private final int amount;
	public HelloDto() { //생성자는 임시 데이터 클래스값을 초기화하는 역할이다.
		this.name = "";
		this.amount = 0;
	}
	
}
