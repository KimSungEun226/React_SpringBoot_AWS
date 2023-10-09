package com.huibigo.service;

import com.huibigo.model.UserEntity;
import com.huibigo.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserEntity create(final UserEntity userEntity) {
		if(userEntity ==null || userEntity.getUsername() == null) {
			throw new RuntimeException("Invalid arguments");
		}
		final String username = userEntity.getUsername();
		if(userRepository.existsByUsername(username)) {
			log.warn("Username already exists");
			throw new RuntimeException("Username already exists");
		}
		
		return userRepository.save(userEntity);
	}

}
