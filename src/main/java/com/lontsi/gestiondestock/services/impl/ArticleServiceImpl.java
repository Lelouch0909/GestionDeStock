package com.lontsi.gestiondestock.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lontsi.gestiondestock.dto.ArticleDto;
import com.lontsi.gestiondestock.exception.EntitiesNotFoundException;
import com.lontsi.gestiondestock.exception.ErrorsCodes;
import com.lontsi.gestiondestock.exception.InvalidEntityException;
import com.lontsi.gestiondestock.model.Article;
import com.lontsi.gestiondestock.repository.ArticleRepository;
import com.lontsi.gestiondestock.services.ArticleService;
import com.lontsi.gestiondestock.validator.ArticleValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

	private ArticleRepository articleRepository;

	@Autowired
	public ArticleServiceImpl(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	public void delete(Integer id) {

		if (id == null) {
			log.error("Id Article est null");
		}

		articleRepository.deleteById(id);
	}

	@Override
	public List<ArticleDto> findAll() {
		return articleRepository.findAll().stream().map(ArticleDto::fromEntity).collect(Collectors.toList());

	}

	@Override
	public ArticleDto findByCodeArticle(String codeArticle) {

		if (!StringUtils.hasLength(codeArticle)) {
			log.error("Code Article est null");
			return null;
		}

		Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);

		ArticleDto articleDto = ArticleDto.fromEntity(article.get());

		return Optional.of(articleDto)
				.orElseThrow(() -> new EntitiesNotFoundException(
						"Aucun article avec le code" + "article " + codeArticle + " n a ete trouve",
						ErrorsCodes.ARTICLE_NOT_FOUND));

	}

	@Override
	public ArticleDto findById(Integer id) {

		if (id == null) {
			log.error("Id Article est null");
			return null;
		}

		Optional<Article> article = articleRepository.findById(id);

		ArticleDto articleDto = ArticleDto.fromEntity(article.get());

		return Optional.of(articleDto)
				.orElseThrow(() -> new EntitiesNotFoundException("Aucun article avec l id " + id + " n a ete trouve",
						ErrorsCodes.ARTICLE_NOT_FOUND));

	}

	@Override
	public ArticleDto save(ArticleDto articleDto) {

		List<String> errors = ArticleValidator.validate(articleDto);
		if (!errors.isEmpty()) {
			log.error("Article non valide {}", articleDto);
			throw new InvalidEntityException("l article n est pas valide", ErrorsCodes.ARTICLE_NOT_FOUND, errors);
		}

		return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(articleDto)));
	}

}
