package com.huibigo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO<T>{
	private String error;
	private List<T> datas;
	private T data;

	//페이징처리를 위함
	private long totalPage;
}
