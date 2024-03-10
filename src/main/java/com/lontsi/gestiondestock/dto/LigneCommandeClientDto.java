package com.lontsi.gestiondestock.dto;

import java.math.BigDecimal;
import com.lontsi.gestiondestock.model.LigneCommandeClient;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LigneCommandeClientDto  {


	private ArticleDto article;
	

	private CommandeClientDto commandeclient;
	

	private BigDecimal quantite;

	private BigDecimal prixUnitaire;

	private Integer id;
	  private Integer idEntreprise;

	
	
	
	
public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {
		
		if(ligneCommandeClient==null) {
			return null;
		}

		return	LigneCommandeClientDto.builder()
		        .id(ligneCommandeClient.getId())
		        .idEntreprise(ligneCommandeClient.getIdEntreprise())
				.quantite(ligneCommandeClient.getQuantite())
				.prixUnitaire(ligneCommandeClient.getPrixUnitaire())
				.commandeclient(CommandeClientDto.fromEntity(ligneCommandeClient.getCommandeclient()))
				.article(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
				.build();
				
		
	}
	
	public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto) {
		
		if(ligneCommandeClientDto==null) {
			return null;
		}
		
		LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
	    ligneCommandeClient.setId(ligneCommandeClientDto.getId());
	    ligneCommandeClient.setIdEntreprise(ligneCommandeClientDto.getIdEntreprise());
		ligneCommandeClient.setArticle(ArticleDto.toEntity(ligneCommandeClientDto.getArticle()));
		ligneCommandeClient.setCommandeclient(CommandeClientDto.toEntity(ligneCommandeClientDto.getCommandeclient()));
		ligneCommandeClient.setQuantite(ligneCommandeClientDto.getQuantite());
		ligneCommandeClient.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());	
		return ligneCommandeClient;
	}
}

