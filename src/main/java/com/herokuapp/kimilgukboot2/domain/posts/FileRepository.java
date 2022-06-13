package com.herokuapp.kimilgukboot2.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
	//자동으로 save(), fineAll(), delete() 등등 자동으로 생성된다.
}
