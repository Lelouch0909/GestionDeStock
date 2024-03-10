package com.lontsi.gestiondestock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lontsi.gestiondestock.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {


	Optional<Article> findArticleByCodeArticle(String codeArticle);
	/*
	 * @Query("select a from Article where code = :code and designation= :designation"
	 * ) List<Article> findByCodeArticleAndDesignation(@Param("code") String code,
	 * String designation);
	 */

}
