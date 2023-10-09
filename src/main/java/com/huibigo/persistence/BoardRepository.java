package com.huibigo.persistence;

import com.huibigo.model.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	Page<BoardEntity> findByCategory_CategoryIdOrderByCreateTimeDesc(Long categoryId, Pageable pageable);
	//BoardEntity findByIdAndUser_Id(Long id, String userId);
}
