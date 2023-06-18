package com.huibigo.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huibigo.model.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	
	List<BoardEntity> findByCategory(String category);
	Optional<BoardEntity> findByUserId(String userId);
}
