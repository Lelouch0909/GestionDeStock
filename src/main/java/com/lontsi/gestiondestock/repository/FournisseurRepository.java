package com.lontsi.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lontsi.gestiondestock.model.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {

}
