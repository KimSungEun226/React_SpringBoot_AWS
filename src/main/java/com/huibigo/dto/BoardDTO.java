package com.huibigo.dto;

import com.huibigo.model.BoardCategoryEntity;
import com.huibigo.model.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {
	
	private Long id;
	private String title;
	private String content;
	private String writerId;
	private BoardCategoryEntity category;
	private String createTime;
	private String updateTime;
	
	public BoardDTO(final BoardEntity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		//this.writerId = entity.getUser().getId();
		this.category = entity.getCategory();
		this.createTime = entity.getCreateTime()!=null?entity.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")):null;
		this.updateTime = entity.getUpdateTime()!=null?entity.getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")):null;
	}
	
	public static BoardEntity toEntity(final BoardDTO dto) {
		return BoardEntity.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.content(dto.getContent())
				.category(dto.getCategory())
				.build();
	}
}
