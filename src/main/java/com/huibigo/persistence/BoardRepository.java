package com.huibigo.persistence;

import com.huibigo.model.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	
	List<BoardEntity> findByCategory(String category);
	//BoardEntity findByIdAndUser_Id(Long id, String userId);
}
