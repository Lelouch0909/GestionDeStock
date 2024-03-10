package com.lontsi.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.lontsi.gestiondestock.dto.ArticleDto;

public class ArticleValidator {

	public static List<String> validate(ArticleDto article) {

		List<String> errors = new ArrayList<>();

		if (article == null || !StringUtils.hasLength(article.getDesignation())) {
			errors.add("veuillez renseigner la designation");
		}

		if (article == null || !StringUtils.hasLength(article.getCodeArticle())) {
			errors.add("veuillez renseigner le code");
		}
		if (article == null || article.getPrixUnitaireHt() == null) {
			errors.add("veuillez renseigner le prix unitaire de l article");
		}
		if (article == null || article.getPrixUnitaireTtc() == null) {
			errors.add("veuillez renseigner le prix unitaire TTC de l article");
		}
		if (article == null || article.getTauxTva() == null) {
			errors.add("veuillez renseigner le taux TVA de l article");
		}

		if (article == null || article.getCategory() == null) {
			errors.add("veuillez selectionner les categories");
		}

		return errors;
	}
}
