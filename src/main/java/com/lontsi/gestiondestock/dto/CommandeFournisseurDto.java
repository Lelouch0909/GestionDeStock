package com.lontsi.gestiondestock.dto;

import java.time.Instant;
import java.util.List;

import com.lontsi.gestiondestock.model.CommandeFournisseur;
import com.lontsi.gestiondestock.model.EtatCommande;


import lombok.Builder;
import lombok.Data;


@Data
@Builder

public class CommandeFournisseurDto {

	private String code;
	
	private Instant dateCommande;
	
	  private EtatCommande etatCommande;

	  private Integer idEntreprise;

	
	private FournisseurDto fournisseur;
	
	private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

	private Integer id;
	
	
public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
		
		if(commandeFournisseur==null) {
			return null;
		}
	
	return	CommandeFournisseurDto.builder()
	        .id(commandeFournisseur.getId())
			.fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
			.dateCommande(commandeFournisseur.getDateCommande())
			.code(commandeFournisseur.getCode())
			.etatCommande(commandeFournisseur.getEtatCommande())
	        .idEntreprise(commandeFournisseur.getIdEntreprise())
			.build();
	
		
		
	}
	
	public static CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto) {
		if(commandeFournisseurDto==null) {
			return null;
		}
		
		CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
	    commandeFournisseur.setId(commandeFournisseurDto.getId());
		commandeFournisseur.setFournisseur(FournisseurDto.toEntity(commandeFournisseurDto.getFournisseur()));
		commandeFournisseur.setCode(commandeFournisseur.getCode());
		commandeFournisseur.setDateCommande(commandeFournisseur.getDateCommande());
		 commandeFournisseur.setIdEntreprise(commandeFournisseurDto.getIdEntreprise());
		    commandeFournisseur.setEtatCommande(commandeFournisseurDto.getEtatCommande());
		return commandeFournisseur;
	}
}
