package com.huibigo.service;

import com.huibigo.model.BoardCategoryEntity;
import com.huibigo.persistence.BoardCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BoardCategoryService {
	
	@Autowired
	private BoardCategoryRepository repository;

	public List<BoardCategoryEntity> retrieve() {
		return repository.findAll();
	}

	public Optional<BoardCategoryEntity> retrieveCategoryName(Long categoryId) {
		return repository.findById(categoryId);
	}

}
