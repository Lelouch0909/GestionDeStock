package com.lontsi.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lontsi.gestiondestock.model.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {

}
