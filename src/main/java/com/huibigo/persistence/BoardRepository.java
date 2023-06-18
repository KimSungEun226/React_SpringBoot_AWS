package com.huibigo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huibigo.model.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	
	List<BoardEntity> findByCategory(String category);
	BoardEntity findByIdAndUser_Id(Long id, String userId);
}
