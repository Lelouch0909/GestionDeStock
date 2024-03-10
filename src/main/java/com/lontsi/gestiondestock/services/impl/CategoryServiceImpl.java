package com.lontsi.gestiondestock.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lontsi.gestiondestock.dto.CategoryDto;
import com.lontsi.gestiondestock.exception.EntitiesNotFoundException;
import com.lontsi.gestiondestock.exception.ErrorsCodes;
import com.lontsi.gestiondestock.exception.InvalidEntityException;
import com.lontsi.gestiondestock.model.Category;
import com.lontsi.gestiondestock.repository.CategoryRepository;
import com.lontsi.gestiondestock.services.CategoryService;
import com.lontsi.gestiondestock.validator.CategoryValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{

	private CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository=categoryRepository;
	}
	
	@Override
	public void delete(Integer id) {
			
		if (id==null) {
			log.error("id de la categorie nulle");
		}
		categoryRepository.deleteById(id);
	}

	@Override
	public List<CategoryDto> findAll() {
		return	categoryRepository.findAll().stream().map(CategoryDto::fromEntity).collect(Collectors.toList()) ;
	}

	@Override
	public CategoryDto findByCode(String code) {

		if (!StringUtils.hasLength(code)) {
			log.error("code Categorie est nul");
		}
		Optional<Category> category= categoryRepository.findByCode(code);
		CategoryDto categoryDto = CategoryDto.fromEntity(category.get());
		return Optional.of(categoryDto).orElseThrow(() -> new EntitiesNotFoundException("aucune categorie trouve avec le code "+code,ErrorsCodes.CATEGORY_NOT_FOUND));
	}

	@Override
	public CategoryDto findById(Integer id) {

		if (id==null) {
			log.error("id categorie nulle");
		}
		Optional<Category> category = categoryRepository.findById(id);
		CategoryDto categoryDto = CategoryDto.fromEntity(category.get());
		
		return Optional.of(categoryDto).orElseThrow(() -> new EntitiesNotFoundException("aucune "
				+ "categorie avec l id " +id +" trouve",ErrorsCodes.CATEGORY_NOT_FOUND));
	}

	@Override
	public CategoryDto save(CategoryDto categoryDto) {
	List<String> error = CategoryValidator.validator(categoryDto);
	if (!error.isEmpty()) {
		log.error("categorie non valide",categoryDto);
		throw new InvalidEntityException("la categorie n est pas valide",ErrorsCodes.CATEGORY_NOT_VALID,error);
	}
	
	return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(categoryDto)));
	}

}
