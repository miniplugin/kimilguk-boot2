package com.herokuapp.kimilgukboot2.service.simple_users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.kimilgukboot2.domain.simple_users.SimpleUsers;
import com.herokuapp.kimilgukboot2.domain.simple_users.SimpleUsersRepository;
import com.herokuapp.kimilgukboot2.web.dto.SimpleUsersDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SimpleUsersService {
	private final SimpleUsersRepository simpleUsersRepository;
	//CRUD 메서드 추가
	@Transactional//저장:Create
	public Long save(SimpleUsersDto requestDto) {
		return simpleUsersRepository.save(requestDto.toEntity()).getId();
	}
	@Transactional//읽기:1명회원 Read:username 으로 검색해야한다.
	public SimpleUsersDto findByName(String username) {
		SimpleUsers entity = simpleUsersRepository.findByName(username);
		return new SimpleUsersDto(entity);
	}
	@Transactional//읽기:전체회원 Read
	public Page<SimpleUsers> usersList(Pageable pageable) {
		return simpleUsersRepository.findAll(pageable);
	}
	@Transactional//수정:Update는 엔티티의 값만수정하면 레포지토리 없이 DB값도 연동된다
	public Long update(Long id, SimpleUsersDto requestDto) {
		SimpleUsers simpleUsers = simpleUsersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
		simpleUsers.update(requestDto.getUsername(), requestDto.getPassword(), requestDto.getRole(), requestDto.getEnabled());
		return id;
	}
	@Transactional//삭제:Delete 1명만 삭제됨
	public void delete(Long id) {
		SimpleUsers simpleUsers = simpleUsersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
		simpleUsersRepository.delete(simpleUsers);
	}
}
