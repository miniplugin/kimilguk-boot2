package com.herokuapp.kimilgukboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing //공통클래스인 등록일,수정일이 자동 입력처리가 된다.
@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
public class KimilgukBoot2Application {

	public static void main(String[] args) {
		SpringApplication.run(KimilgukBoot2Application.class, args);
	}

}
