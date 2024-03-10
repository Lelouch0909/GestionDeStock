package com.lontsi.gestiondestock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lontsi.gestiondestock.model.LigneVente;

public interface LigneVentesRepository extends JpaRepository<LigneVente, Integer> {

	List<LigneVente> findAllByArticleId(Integer idArticle);

	List<LigneVente> findAllByVenteId(Integer id);

}
