package com.huibigo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserEntity;
import com.huibigo.model.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, String> {

}
