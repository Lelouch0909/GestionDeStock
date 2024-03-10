package com.lontsi.gestiondestock.services;

import java.util.List;

import com.lontsi.gestiondestock.dto.ArticleDto;

public interface ArticleService {

	void delete(Integer id);

	List<ArticleDto> findAll();

	ArticleDto findByCodeArticle(String codeArticle);

	ArticleDto findById(Integer id);

	ArticleDto save(ArticleDto articleDto);
}
