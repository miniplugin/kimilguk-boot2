package com.herokuapp.kimilgukboot2.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.herokuapp.kimilgukboot2.domain.posts.Posts;
import com.herokuapp.kimilgukboot2.domain.posts.PostsRepository;
import com.herokuapp.kimilgukboot2.service.posts.FileService;
import com.herokuapp.kimilgukboot2.service.posts.ManyFileService;
import com.herokuapp.kimilgukboot2.web.dto.FileDto;
import com.herokuapp.kimilgukboot2.web.dto.ManyFileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ManyFileUtilsApi {
	private final ManyFileService fileService;
	private final PostsRepository postsRepository;
	
	@GetMapping("api/many_file_download/{fileId}")
	public ResponseEntity<Resource> filedownload(@PathVariable("fileId") Long fileId) throws IOException {
		//Resource 는 바이트기반의 형태로 데이터를 반환할 때 ResponseEntity 객체에 담아서 반환한다.
		ManyFileDto fileDto = fileService.getFile(fileId);//DB에서 파일정보를 가져온다.
		Path filePath = Paths.get(fileDto.getFilePath());//파일경로를 가져온다.
		Resource resource = new InputStreamResource(Files.newInputStream(filePath));//Resource는 바이트기반이기 때문에 스트림객체로 가져온다.
		String encOrigFilename = URLEncoder.encode(fileDto.getOrigFilename());//ie 브라우저에서 한글파일명이 깨질때 방지하는 코드(크로스브라우징 체크) 
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\""+encOrigFilename+"\"")
				.body(resource);//\" 큰따옴표안에 큰따옴표를 사용할 때 역슬래쉬가 사용된다.
	}
	
	@DeleteMapping("/api/many_file_delete/{fileId}")
	public ResponseEntity<Map<String,Object>> file_delete(@PathVariable("fileId") Long fileId) {//삭제 후 반환 값으로 json 형태로 데이터를 반환할 때 ResponseEntity 객체에 담아서 반환한다.
		Map<String,Object> jsonResult = new HashMap<>();//json 형태의 객체 생성
		ResponseEntity<Map<String,Object>> result = null;//json 반환값 초기화
		ManyFileDto fileDto = fileService.getFile(fileId);
		Path filePath = Paths.get(fileDto.getFilePath());//파일경로를 가져온다.
		File target = new File(filePath.toString());//해당경로의 파일객체를 가져온다.
		if(target.exists()) {
			target.delete();//파일객체가 존재한다면, 실제파일을 삭제한다.
			fileService.deleteFile(fileId);//DB에서 파일정보를 삭제한다.
			jsonResult.put("success", "OK");//json 형태에 OK값을 저장한다.
			result = new ResponseEntity<Map<String,Object>>(jsonResult, HttpStatus.OK);
		}
		return result;
	}
	
	@PostMapping("/api/many_file_upload")
	public Long uploadFile(@RequestParam("many_file")MultipartFile uploadFile,@RequestParam("post_id")Long post_id) {
		Long fileId = null;
		String origFilename = uploadFile.getOriginalFilename();
		UUID uid = UUID.randomUUID();
		String filename = uid.toString()+"."+StringUtils.getFilenameExtension(origFilename);
		String uploadPath = "/tmp";//클라우드에는 기본으로 존재 단, 개발PC에서는 tmp폴더를 생성해야 한다.
		String filePath = Paths.get(uploadPath, filename).toString();
		try {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			stream.write(uploadFile.getBytes());//실제저장
			stream.close();//메모리 객체삭제
			Posts posts = postsRepository.findById(post_id).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다."));
			ManyFileDto fileDto = new ManyFileDto();
			fileDto.setOrigFilename(origFilename);
			fileDto.setFilename(filename);
			fileDto.setFilePath(filePath);
			fileDto.setPosts(posts);
			fileId = fileService.saveFile(fileDto);
		} catch (IOException e) {
			// 에러발생시 프로그램 중단없이 계속 진행하는 역할
			e.printStackTrace();
		}
		return fileId;
	}
}
