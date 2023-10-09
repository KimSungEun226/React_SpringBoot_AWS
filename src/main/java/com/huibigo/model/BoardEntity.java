package com.huibigo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Board")
@EntityListeners(AuditingEntityListener.class)
public class BoardEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@Lob
	private String content;
	
	private String category;

	@CreatedDate
	private LocalDateTime createTime;

	@LastModifiedDate
	private LocalDateTime updateTime;
	
//	@JoinColumn(name = "user_id", nullable = false)
//	@ManyToOne(fetch = FetchType.LAZY)
//	private UserEntity user;
}
