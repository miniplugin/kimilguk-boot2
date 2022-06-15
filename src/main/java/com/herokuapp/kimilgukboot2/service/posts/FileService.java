package com.herokuapp.kimilgukboot2.service.posts;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.kimilgukboot2.domain.posts.File;
import com.herokuapp.kimilgukboot2.domain.posts.FileRepository;
import com.herokuapp.kimilgukboot2.web.dto.FileDto;

@Service
public class FileService {
	private FileRepository fileRepository;//아직은 객체가 아니다.(사용하지 못한다.)
	//고전적인 방법으로 객체를 생성하는 것을 확인(아래)
	public FileService(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}
	//CR-D 서비스를 만든다.
	@Transactional
	public void deleteFile(Long id) {//파일테이블의 고유키값인 id를 매개변수로 받는다.
		fileRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 파일이 없습니다."));
		fileRepository.deleteById(id);//삭제는 반환값이 없음=void
	}
	@Transactional
	public Long saveFile(FileDto fileDto) {
		return fileRepository.save(fileDto.toEntity()).getId();//저장 후 id고유값을 반환
	}
	@Transactional
	public FileDto getFile(Long id) {
		System.out.println("여기까지1" + id);
		File file = fileRepository.findById(id).get();//DB의 파일 테이블에서 가져온 객체를 get()메소드로 반환한다.
		System.out.println("여기까지2");
		return FileDto.builder()
				.id(id)
				.origFilename(file.getOrigFilename())
				.filename(file.getFilename())
				.filePath(file.getFilePath())
				.build();
	}
}
