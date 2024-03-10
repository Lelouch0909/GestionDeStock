package com.lontsi.gestiondestock.dto;

import java.util.List;

import com.lontsi.gestiondestock.model.Fournisseur;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class FournisseurDto {


	private String nom;
	
	private String prenom;
	
	  private Integer idEntreprise;

	private String photo;
	
	private String mail;
	
	private String numTel;
	
	private AdresseDto adresse;
	
	private List<CommandeFournisseurDto> commandeFournisseurs;

	private Integer id;
	
public static FournisseurDto fromEntity(Fournisseur fournisseur) {
		
		if(fournisseur==null) {
			return null;
		}
	
	return	FournisseurDto.builder()
	        .id(fournisseur.getId())
			.nom(fournisseur.getNom())
			.prenom(fournisseur.getPrenom())
			.mail(fournisseur.getMail())
			.photo(fournisseur.getPhoto())
			.numTel(fournisseur.getNumTel())
			.adresse(AdresseDto.fromEntity(fournisseur.getAdresse()))
	        .idEntreprise(fournisseur.getIdEntreprise())
			.build();
	
		
		
	}
	
	public static Fournisseur toEntity(FournisseurDto fournisseurDto) {
		if(fournisseurDto==null) {
			return null;
		}
		
		Fournisseur fournisseur = new Fournisseur();
	    fournisseur.setId(fournisseurDto.getId());
	    fournisseur.setIdEntreprise(fournisseurDto.getIdEntreprise());
		fournisseur.setNom(fournisseurDto.getNom());
		fournisseur.setPrenom(fournisseurDto.getPrenom());
		fournisseur.setMail(fournisseurDto.getMail());
		fournisseur.setPhoto(fournisseurDto.getPhoto());
		fournisseur.setNumTel(fournisseurDto.getNumTel());
		fournisseur.setAdresse(AdresseDto.toEntity(fournisseurDto.getAdresse()));

		
		return fournisseur;
	}
}
