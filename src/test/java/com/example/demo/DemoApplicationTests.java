package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.huibigo.model.BoardEntity;
import com.huibigo.persistence.BoardRepository;

@SpringBootTest
class DemoApplicationTests {

	//@Autowired
	//BoardRepository bR;
	
	@Test
	void contextLoads() {
		System.out.println("안녕하세요ㅗ..");
		BoardEntity b = new BoardEntity();
		//b.setId(123L);
		b.setTitle("안녕하세욥");
		b.setWriter("존성은");
		//bR.save(b);
	}

}
