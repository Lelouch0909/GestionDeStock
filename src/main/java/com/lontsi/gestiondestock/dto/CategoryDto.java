package com.lontsi.gestiondestock.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lontsi.gestiondestock.model.Category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto  {

	private Integer id;
	private Integer idEntreprise;
	
	private String code;
	
	private String designation;
	
	@JsonIgnore
	private List<ArticleDto> articles;
	

	
	public static CategoryDto fromEntity(Category category) {
		
		if(category==null) {
			return null;
		}
	
	return	CategoryDto.builder()
	        .id(category.getId())
	        .idEntreprise(category.getIdEntreprise())
			.code(category.getCode())
			.id(category.getId())
			.designation(category.getDesignation())
			.build();
	
		
		
	}
	
	public static Category toEntity(CategoryDto categoryDto) {
		if(categoryDto==null) {
			return null;
		}
		
		Category category = new Category();
	    category.setId(categoryDto.getId());
	    category.setIdEntreprise(categoryDto.getIdEntreprise());
		category.setId(categoryDto.getId());
		category.setCode(categoryDto.getCode());
		category.setDesignation(categoryDto.getDesignation());
		
		return category;
	}
}
