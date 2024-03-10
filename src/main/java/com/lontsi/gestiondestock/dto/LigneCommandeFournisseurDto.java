package com.lontsi.gestiondestock.dto;

import java.math.BigDecimal;

import com.lontsi.gestiondestock.model.LigneCommandeFournisseur;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LigneCommandeFournisseurDto {

	private ArticleDto article;
	
	private CommandeFournisseurDto commandefournisseur;
	
	  private Integer idEntreprise;

	private BigDecimal quantite;

	private BigDecimal prixUnitaire;

	private Integer id;
	
	

	
public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
		
		if(ligneCommandeFournisseur==null) {
			return null;
		}

		return	LigneCommandeFournisseurDto.builder()
		        .idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
		        .id(ligneCommandeFournisseur.getId())
				.quantite(ligneCommandeFournisseur.getQuantite())
				.prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
				.commandefournisseur(CommandeFournisseurDto.fromEntity(ligneCommandeFournisseur.getCommandefournisseur()))
				.article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
				.build();
				
		
	}
	
	public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
		
		if(ligneCommandeFournisseurDto==null) {
			return null;
		}
		
		LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
	    ligneCommandeFournisseur.setId(ligneCommandeFournisseurDto.getId());
	    ligneCommandeFournisseur.setIdEntreprise(ligneCommandeFournisseurDto.getIdEntreprise());
		ligneCommandeFournisseur.setArticle(ArticleDto.toEntity(ligneCommandeFournisseurDto.getArticle()));
		ligneCommandeFournisseur.setCommandefournisseur(CommandeFournisseurDto.toEntity(ligneCommandeFournisseurDto.getCommandefournisseur()));
		ligneCommandeFournisseur.setQuantite(ligneCommandeFournisseurDto.getQuantite());
		ligneCommandeFournisseur.setPrixUnitaire(ligneCommandeFournisseurDto.getPrixUnitaire());	
		
		return ligneCommandeFournisseur;
	}
}
