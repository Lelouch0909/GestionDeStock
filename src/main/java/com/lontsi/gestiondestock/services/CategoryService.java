 package com.lontsi.gestiondestock.services;

import java.util.List;

import com.lontsi.gestiondestock.dto.CategoryDto;

public interface CategoryService {

	void delete(Integer id);

	List<CategoryDto> findAll();

	CategoryDto findByCode(String code);

	CategoryDto findById(Integer id);

	CategoryDto save(CategoryDto categoryDto);

}
