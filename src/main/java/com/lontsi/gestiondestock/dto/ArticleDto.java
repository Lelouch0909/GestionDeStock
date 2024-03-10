package com.lontsi.gestiondestock.dto;

import java.math.BigDecimal;

import com.lontsi.gestiondestock.model.Article;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleDto  {


	private String codeArticle;
	
	private String designation;
	
	private BigDecimal prixUnitaireHt;
	
	private BigDecimal prixUnitaireTtc;
	
	private BigDecimal tauxTva;
	
	private String photo;
	
	private CategoryDto category;

	private Integer id;
	
	  private Integer idEntreprise;

	
	
	
public static ArticleDto fromEntity(Article article) {
		
		if(article==null) {
			return null;
		}
	
	return	ArticleDto.builder()
	        .id(article.getId())
	        .idEntreprise(article.getIdEntreprise())
			.codeArticle(article.getCodeArticle())
			.designation(article.getDesignation())
			.prixUnitaireHt(article.getPrixUnitaireHt())
			.prixUnitaireTtc(article.getPrixUnitaireTtc())
			.tauxTva(article.getTauxTva())
			.photo(article.getPhoto())
			.category(CategoryDto.fromEntity(article.getCategory()))
			.build();
	
		
		
	}
	
	public static Article toEntity(ArticleDto articleDto) {
		
		if(articleDto==null) {
			return null;
		}
		
		Article article = new Article();
	    article.setId(articleDto.getId());
		article.setCategory(CategoryDto.toEntity(articleDto.getCategory()));
		article.setCodeArticle(articleDto.getCodeArticle());
		article.setDesignation(articleDto.getDesignation());
		article.setPhoto(articleDto.getPhoto());
		article.setTauxTva(articleDto.getTauxTva());
	    article.setIdEntreprise(articleDto.getIdEntreprise());
		article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
		article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
		
		return article;
	}
}
