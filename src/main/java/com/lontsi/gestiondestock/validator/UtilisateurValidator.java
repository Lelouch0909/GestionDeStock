package com.lontsi.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.lontsi.gestiondestock.dto.UtilisateurDto;

public class UtilisateurValidator {

public static List<String> validate(UtilisateurDto utilisateurDto){
		
		List<String> errors = new ArrayList<>();
		
		if (utilisateurDto==null||!StringUtils.hasLength(utilisateurDto.getNom())) {
			errors.add("veuillez renseigner le nom");
		}
		
		if (utilisateurDto==null||!StringUtils.hasLength(utilisateurDto.getPrenom())) {
			errors.add("veuillez renseigner le prenom");
		}
		if (utilisateurDto==null||!StringUtils.hasLength(utilisateurDto.getMotDePasse())) {
			errors.add("veuillez renseigner le mot de passe");
		}
		if (utilisateurDto==null||utilisateurDto.getAdresse()==null) {
			errors.add("veuillez renseigner l adresse");
		}
		else {
			if (utilisateurDto==null||!StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())) {
				errors.add("le champ adresse 1 est obligatoire");
			}
			
			if (utilisateurDto==null||!StringUtils.hasLength(utilisateurDto.getAdresse().getVille())) {
				errors.add("le champ ville est obligatoire");
			}
			if (utilisateurDto==null||!StringUtils.hasLength(utilisateurDto.getAdresse().getPays())) {
				errors.add("le champ pays est obligatoire");
			}
			if (utilisateurDto==null||!StringUtils.hasLength(utilisateurDto.getAdresse().getCodePostale())) {
				errors.add("le champ code postale est obligatoire");
			}
		}
		if (utilisateurDto==null||utilisateurDto.getDateDeNaissance()==null) {
			errors.add("veuillez renseigner la date de naissance");
		}
		
		if (utilisateurDto==null||utilisateurDto.getEmail()==null) {
			errors.add("veuillez renseigner l email");
		}
		
		
		return errors;		
	}
}
