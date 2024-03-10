package com.lontsi.gestiondestock.services.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lontsi.gestiondestock.dto.ArticleDto;
import com.lontsi.gestiondestock.dto.LigneVenteDto;
import com.lontsi.gestiondestock.dto.MvtStkDto;
import com.lontsi.gestiondestock.dto.VentesDto;
import com.lontsi.gestiondestock.exception.EntitiesNotFoundException;
import com.lontsi.gestiondestock.exception.ErrorsCodes;
import com.lontsi.gestiondestock.exception.InvalidEntityException;
import com.lontsi.gestiondestock.exception.InvalidOperationException;
import com.lontsi.gestiondestock.model.Article;
import com.lontsi.gestiondestock.model.LigneVente;
import com.lontsi.gestiondestock.model.SourceMvtStk;
import com.lontsi.gestiondestock.model.TypeMvtStk;
import com.lontsi.gestiondestock.model.Ventes;
import com.lontsi.gestiondestock.repository.ArticleRepository;
import com.lontsi.gestiondestock.repository.LigneVentesRepository;
import com.lontsi.gestiondestock.repository.VentesRepository;
import com.lontsi.gestiondestock.services.MvtStkService;
import com.lontsi.gestiondestock.services.VentesService;
import com.lontsi.gestiondestock.validator.VentesValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

	private ArticleRepository articleRepository;
	private VentesRepository ventesRepository;
	private LigneVentesRepository ligneVenteRepository;
	private MvtStkService mvtStkService;

	@Autowired
	public VentesServiceImpl(ArticleRepository articleRepository, VentesRepository ventesRepository,
			LigneVentesRepository ligneVenteRepository, MvtStkService mvtStkService) {
		this.articleRepository = articleRepository;
		this.ventesRepository = ventesRepository;
		this.ligneVenteRepository = ligneVenteRepository;
		this.mvtStkService = mvtStkService;
	}

	@Override
	public VentesDto save(VentesDto dto) {
		List<String> errors = VentesValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Ventes n'est pas valide");
			throw new InvalidEntityException("L'objet vente n'est pas valide", ErrorsCodes.VENTE_NOT_VALID, errors);
		}

		List<String> articleErrors = new ArrayList<>();

		dto.getLigneVentes().forEach(ligneVenteDto -> {
			Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
			if (article.isEmpty()) {
				articleErrors.add("Aucun article avec l'ID " + ligneVenteDto.getArticle().getId()
						+ " n'a ete trouve dans la BDD");
			}
		});

		if (!articleErrors.isEmpty()) {
			log.error("One or more articles were not found in the DB, {}", errors);
			throw new InvalidEntityException("Un ou plusieurs articles n'ont pas ete trouve dans la BDD",
					ErrorsCodes.VENTE_NOT_VALID, errors);
		}

		Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(dto));

		dto.getLigneVentes().forEach(ligneVenteDto -> {
			LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
			ligneVente.setVente(savedVentes);
			ligneVenteRepository.save(ligneVente);
			updateMvtStk(ligneVente);
		});

		return VentesDto.fromEntity(savedVentes);
	}

	@Override
	public VentesDto findById(Integer id) {
		if (id == null) {
			log.error("Ventes ID is NULL");
			return null;
		}
		return ventesRepository.findById(id).map(VentesDto::fromEntity)
				.orElseThrow(() -> new EntitiesNotFoundException("Aucun vente n'a ete trouve dans la BDD",
						ErrorsCodes.VENTE_NOT_FOUND));
	}

	@Override
	public VentesDto findByCode(String code) {
		if (!StringUtils.hasLength(code)) {
			log.error("Vente CODE is NULL");
			return null;
		}
		return ventesRepository.findVentesByCode(code).map(VentesDto::fromEntity).orElseThrow(
				() -> new EntitiesNotFoundException("Aucune vente client n'a ete trouve avec le CODE " + code,
						ErrorsCodes.VENTE_NOT_VALID));
	}

	@Override
	public List<VentesDto> findAll() {
		return ventesRepository.findAll().stream().map(VentesDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Vente ID is NULL");
			return;
		}
		List<LigneVente> ligneVentes = ligneVenteRepository.findAllByVenteId(id);
		if (!ligneVentes.isEmpty()) {
			throw new InvalidOperationException("Impossible de supprimer une vente ...",
					ErrorsCodes.VENTE_ALREADY_IN_USE);
		}
		ventesRepository.deleteById(id);
	}

	private void updateMvtStk(LigneVente lig) {
		MvtStkDto mvtStkDto = MvtStkDto.builder().article(ArticleDto.fromEntity(lig.getArticle()))
				.dateMvt(Instant.now()).typeMvt(TypeMvtStk.SORTIE).sourceMvt(SourceMvtStk.VENTE)
				.quantite(lig.getQuantite()).idEntreprise(lig.getIdEntreprise()).build();
		mvtStkService.sortieStock(mvtStkDto);
	}
}
