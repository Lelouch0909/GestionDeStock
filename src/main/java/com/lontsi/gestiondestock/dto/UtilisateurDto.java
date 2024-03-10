package com.lontsi.gestiondestock.dto;

import java.time.Instant;
import java.util.List;

import com.lontsi.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilisateurDto  {

	
	private String nom;

	private String prenom;

	private String email;
	

	private Instant dateDeNaissance;

	private String motDePasse;
	

	private AdresseDto adresse;
	

	private String photo;
	

	private EntrepriseDto entreprise;
	
	private List<RolesDto> roles;

	private Integer id;
	

	
public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
		
		if(utilisateur==null) {
			return null;
		}
	
	return	UtilisateurDto.builder()
	        .id(utilisateur.getId())
			.nom(utilisateur.getNom())
			.prenom(utilisateur.getPrenom())
			.email(utilisateur.getEmail())
			.dateDeNaissance(utilisateur.getDateDeNaissance())
			.motDePasse(utilisateur.getMotDePasse())
			.photo(utilisateur.getPhoto())
			.adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
			.entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
			.build();
	
		
		
	}
	
	public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {
		
		if(utilisateurDto==null) {
			return null;
		}
		
		Utilisateur utilisateur = new Utilisateur();
	    utilisateur.setId(utilisateurDto.getId());
		utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresse()));
		utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));
		utilisateur.setNom(utilisateurDto.getNom());
		utilisateur.setPrenom(utilisateurDto.getPrenom());
		utilisateur.setPhoto(utilisateurDto.getPhoto());
		utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
		utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
		utilisateur.setEmail(utilisateurDto.getEmail());
		
		return utilisateur;
	}
}
