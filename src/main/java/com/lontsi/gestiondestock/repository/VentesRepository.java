package com.lontsi.gestiondestock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lontsi.gestiondestock.model.Ventes;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {

	Optional<Ventes> findVentesByCode(String code);

}
