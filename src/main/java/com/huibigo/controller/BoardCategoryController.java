package com.huibigo.controller;

import com.huibigo.dto.BoardCategoryDTO;
import com.huibigo.dto.ResponseDTO;
import com.huibigo.model.BoardCategoryEntity;
import com.huibigo.service.BoardCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/boardCategory")
public class BoardCategoryController {
	
	@Autowired
	private BoardCategoryService service;

	@GetMapping
	public ResponseEntity<?> retrieveBoardCategoryList() {
		
		List<BoardCategoryEntity> entities = service.retrieve();
		
		List<BoardCategoryDTO> dtos = entities.stream().map(BoardCategoryDTO::new).collect(Collectors.toList());
		
		ResponseDTO<BoardCategoryDTO> response = ResponseDTO.<BoardCategoryDTO>builder().datas(dtos).build();
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<?> retrieveBoardCategoryName(@PathVariable(required = true) Long categoryId) {

		Optional<BoardCategoryEntity> boardCategoryEntity = service.retrieveCategoryName(categoryId);

		BoardCategoryDTO boardCategoryDTO = null;
		if(boardCategoryEntity.isPresent()) boardCategoryDTO = new BoardCategoryDTO(boardCategoryEntity.get());

		ResponseDTO<BoardCategoryDTO> response = ResponseDTO.<BoardCategoryDTO>builder().data(boardCategoryDTO).build();
		return ResponseEntity.ok().body(response);
	}
}
