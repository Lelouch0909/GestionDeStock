package com.lontsi.gestiondestock.services;

import java.util.List;

import com.lontsi.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import com.lontsi.gestiondestock.dto.UtilisateurDto;

public interface UtilisateurService {

	void delete(Integer id);
	
	UtilisateurDto save(UtilisateurDto utilisateurDto);

	UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto changerMotDePasseUtilisateurDto);
	
	UtilisateurDto findById(Integer id);
		
	List<UtilisateurDto> findAll();
}
