package com.huibigo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huibigo.dto.BoardDTO;
import com.huibigo.dto.ResponseDTO;
import com.huibigo.model.BoardEntity;
import com.huibigo.model.UserEntity;
import com.huibigo.service.BoardService;

@RestController
@RequestMapping("board") 
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@PostMapping
	public ResponseEntity<?> createBoard(@AuthenticationPrincipal String userId, 
			@RequestBody BoardDTO dto) {
		try {
			BoardEntity entity = BoardDTO.toEntity(dto);
			entity.setId(null);
			entity.setWriter(UserEntity.builder().id(userId).build());
			BoardEntity boardEntity = service.create(entity);
			
			BoardDTO boardDto = new BoardDTO(boardEntity);
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(boardDto).build();
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> retrieveBoardList(@RequestBody(required = false) BoardDTO boardDTO) {
		
		List<BoardEntity> entities = null;
		if(boardDTO == null) entities = service.retrieve();
		else entities = service.retrieveOptional(BoardDTO.toEntity(boardDTO));
		
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
		
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().datas(dtos).build();
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping
	public ResponseEntity<?> updateBoard(@AuthenticationPrincipal String userId,
			@RequestBody BoardDTO dto) {
		
		BoardEntity entity = BoardDTO.toEntity(dto);
		entity.setWriter(UserEntity.builder().id(userId).build());
		BoardEntity updatedEntity = service.update(entity);
		
		BoardDTO boardDto = new BoardDTO(updatedEntity);
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(boardDto).build();
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteBoard(@AuthenticationPrincipal String userId,
			@RequestBody BoardDTO dto) {
		try {
			
			BoardEntity entity = BoardDTO.toEntity(dto);
			entity.setWriter(UserEntity.builder().id(userId).build());
			service.delete(entity);
			
			return ResponseEntity.ok().body(null);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
}
