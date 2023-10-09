package com.huibigo.dto;

import com.huibigo.model.BoardCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardCategoryDTO {

    private Long categoryId;
    private String categoryName;

    public BoardCategoryDTO(final BoardCategoryEntity entity) {
        this.categoryId = entity.getCategoryId();
        this.categoryName = entity.getCategoryName();    }

    public static BoardCategoryEntity toEntity(final BoardCategoryDTO dto) {
        return BoardCategoryEntity.builder()
                .categoryId(dto.getCategoryId())
                .categoryName(dto.getCategoryName())
                .build();
    }
}
