package com.lontsi.gestiondestock.dto;

import java.math.BigDecimal;

import com.lontsi.gestiondestock.model.LigneVente;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LigneVenteDto {

	private VentesDto vente;

	private ArticleDto article;

	private BigDecimal quantite;

	private BigDecimal prixUnitaire;

	private Integer id;

	private Integer idEntreprise;

	public static LigneVenteDto fromEntity(LigneVente ligneVente) {

		if (ligneVente == null) {
			return null;
		}

		return LigneVenteDto.builder().id(ligneVente.getId()).vente(VentesDto.fromEntity(ligneVente.getVente()))
				.quantite(ligneVente.getQuantite()).article(ArticleDto.fromEntity(ligneVente.getArticle()))
				.prixUnitaire(ligneVente.getPrixUnitaire()).idEntreprise(ligneVente.getIdEntreprise()).build();

	}

	public static LigneVente toEntity(LigneVenteDto ligneVenteDto) {

		if (ligneVenteDto == null) {
			return null;
		}

		LigneVente ligneVente = new LigneVente();
		ligneVente.setId(ligneVenteDto.getId());
		ligneVente.setIdEntreprise(ligneVenteDto.getIdEntreprise());
		ligneVente.setVente(VentesDto.toEntity(ligneVenteDto.getVente()));
		ligneVente.setArticle(ArticleDto.toEntity(ligneVenteDto.getArticle()));
		ligneVente.setQuantite(ligneVenteDto.getQuantite());
		ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());

		return ligneVente;
	}
}
