package com.lontsi.gestiondestock.dto;

import java.util.List;

import com.lontsi.gestiondestock.model.Entreprise;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class EntrepriseDto{

	private String nom;
	
	private String description;
	
	private String photo;
	
	private String codeFiscal;
	
	
	private String email;
	
	private String numTel;
	
	private String siteWeb;
	
	
	private AdresseDto adresse;
	
	private List<UtilisateurDto> utilisateur;

	private Integer id;
	
public static EntrepriseDto fromEntity(Entreprise entreprise) {
		
		if(entreprise==null) {
			return null;
		}
	
	return	EntrepriseDto.builder()
			.nom(entreprise.getNom())
	        .id(entreprise.getId())
			.description(entreprise.getDescription())
			.email(entreprise.getEmail())
			.codeFiscal(entreprise.getCodeFiscal())
			.siteWeb(entreprise.getSiteWeb())
			.photo(entreprise.getPhoto())
			.numTel(entreprise.getNumTel())
			.adresse(AdresseDto.fromEntity(entreprise.getAdresse()))
			.build();
	
		
		
	}
	
	public static Entreprise toEntity(EntrepriseDto entrepriseDto) {
		if(entrepriseDto==null) {
			return null;
		}
		
		Entreprise entreprise = new Entreprise();
	    entreprise.setId(entrepriseDto.getId());
		entreprise.setNom(entrepriseDto.getNom());
		entreprise.setDescription(entrepriseDto.getDescription());
		entreprise.setEmail(entrepriseDto.getEmail());
		entreprise.setPhoto(entrepriseDto.getPhoto());
		entreprise.setSiteWeb(entrepriseDto.getSiteWeb());
		entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
		entreprise.setNumTel(entrepriseDto.getNumTel());
		entreprise.setAdresse(AdresseDto.toEntity(entrepriseDto.getAdresse()));

		
		return entreprise;
	}
}
