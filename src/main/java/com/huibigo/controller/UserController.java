package com.huibigo.controller;

import com.huibigo.dto.ResponseDTO;
import com.huibigo.dto.UserDTO;
import com.huibigo.model.UserEntity;
import com.huibigo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	//private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@PostMapping("/signup")
	public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
		try {
			if(userDTO == null || userDTO.getPassword() == null) {
				throw new RuntimeException("Invalid Password value.");
			}
		// ��û�� �̿��� ������ ���� �����
		UserEntity user = UserEntity.builder()
				.username(userDTO.getUsername())
				.password(userDTO.getPassword())
				.build();
		// ���񽺸� �̿��� �������͸��� ���� ����
		UserEntity registeredUser = userService.create(user);
		UserDTO responseUserDTO = UserDTO.builder()
				.id(registeredUser.getId())
				.username(registeredUser.getUsername())
				.build();
		return ResponseEntity.ok().body(responseUserDTO);
		} catch (Exception e) {
			// ���� ������ �׻� �ϳ��̹Ƿ� ����Ʈ�� ������ �ϴ� ResponseDTO�� ������� �ʰ� �׳� UserDTO ����.
			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
		UserEntity user = null;

		if(user != null) {
			// ��ū ����
			final String token = null;
			final UserDTO responseUserDTO = UserDTO.builder()
					.username(user.getUsername())
					.id(user.getId())
					.token(token)
					.build();
			return ResponseEntity.ok().body(responseUserDTO);
		}else {
			ResponseDTO responseDTO = ResponseDTO.builder()
					.error("Login failed")
					.build();
			return ResponseEntity
					.badRequest()
					.body(responseDTO);
		}
	}
	
}
