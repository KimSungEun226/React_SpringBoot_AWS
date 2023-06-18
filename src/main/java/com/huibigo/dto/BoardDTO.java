package com.huibigo.dto;

import java.time.LocalDateTime;

import com.huibigo.model.BoardEntity;
import com.huibigo.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {
	
	private Long id; 			// �� ������Ʈ�� ���̵�
	private String title;		// Todo Ÿ��Ʋ ��) � �ϱ�
	private String content;
	private String writerId;
	private String category;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	
	public BoardDTO(final BoardEntity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.writerId = entity.getUser().getId();
		this.category = entity.getCategory();
		this.createTime = entity.getCreateTime();
		this.updateTime = entity.getUpdateTime();
	}
	
	public static BoardEntity toEntity(final BoardDTO dto) {
		return BoardEntity.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.content(dto.getContent())
				.user(UserEntity.builder().id(dto.getWriterId()).build())
				.category(dto.getCategory())
				.createTime(dto.getCreateTime())
				.updateTime(dto.getUpdateTime())
				.build();
	}
}
