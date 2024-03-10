package com.lontsi.gestiondestock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lontsi.gestiondestock.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

	Optional<Utilisateur> findUtilisateurByNom(String nom);
	
	Optional<Utilisateur> findByEmail(String email);
}
