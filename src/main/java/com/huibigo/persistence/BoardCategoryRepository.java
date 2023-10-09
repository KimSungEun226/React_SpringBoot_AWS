package com.huibigo.persistence;

import com.huibigo.model.BoardCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardCategoryRepository extends JpaRepository<BoardCategoryEntity, Long> {

}
