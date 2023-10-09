package com.huibigo.controller;

import com.huibigo.dto.BoardDTO;
import com.huibigo.dto.ResponseDTO;
import com.huibigo.model.BoardEntity;
import com.huibigo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;

	@PostMapping
	public ResponseEntity<?> createBoard(/*@AuthenticationPrincipal*/ String userId,
			@RequestBody BoardDTO dto) {
		try {
			BoardEntity entity = BoardDTO.toEntity(dto);
			entity.setId(null);
			//entity.setUser(UserEntity.builder().id(userId).build());
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
	public ResponseEntity<?> retrieveBoardList(@RequestParam(required = false) String category) {
		
		List<BoardEntity> entities = null;
		if(category == null) entities = service.retrieve();
		else entities = service.retrieveOptional(category);
		
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
		
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().datas(dtos).build();
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> retrieveBoard(@PathVariable(required = true) Long id) {

		Optional<BoardEntity> boardEntity = service.retrieveOptional(id);
		BoardDTO boardDto = null;
		if(boardEntity.isPresent()) boardDto = new BoardDTO(service.retrieveOptional(id).get());

		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(boardDto).build();
		return ResponseEntity.ok().body(response);
	}

	@PutMapping
	public ResponseEntity<?> updateBoard(/*@AuthenticationPrincipal*/ String userId,
			@RequestBody BoardDTO dto) {
		
		BoardEntity entity = BoardDTO.toEntity(dto);
		//entity.setUser(UserEntity.builder().id(userId).build());
		BoardEntity updatedEntity = service.update(entity);
		
		BoardDTO boardDto = new BoardDTO(updatedEntity);
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(boardDto).build();
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteBoard(/*@AuthenticationPrincipal*/ String userId,
			@RequestBody BoardDTO dto) {
		try {
			
			BoardEntity entity = BoardDTO.toEntity(dto);
			//entity.setUser(UserEntity.builder().id(userId).build());
			service.delete(entity);
			
			return ResponseEntity.ok().body(null);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
}
