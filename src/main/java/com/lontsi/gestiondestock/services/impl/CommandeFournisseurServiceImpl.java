package com.lontsi.gestiondestock.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lontsi.gestiondestock.dto.CommandeFournisseurDto;
import com.lontsi.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.lontsi.gestiondestock.exception.EntitiesNotFoundException;
import com.lontsi.gestiondestock.exception.ErrorsCodes;
import com.lontsi.gestiondestock.exception.InvalidEntityException;
import com.lontsi.gestiondestock.model.Article;
import com.lontsi.gestiondestock.model.CommandeFournisseur;
import com.lontsi.gestiondestock.model.Fournisseur;
import com.lontsi.gestiondestock.model.LigneCommandeFournisseur;
import com.lontsi.gestiondestock.repository.ArticleRepository;
import com.lontsi.gestiondestock.repository.CommandeFournisseurRepository;
import com.lontsi.gestiondestock.repository.FournisseurRepository;
import com.lontsi.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.lontsi.gestiondestock.services.CommandeFournisseurService;
import com.lontsi.gestiondestock.validator.CommandeFournisseurValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

	private CommandeFournisseurRepository commandeFournisseurRepository;
	private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
	private FournisseurRepository fournisseurRepository;
	private ArticleRepository articleRepository;

	public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
			LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,
			FournisseurRepository fournisseurRepository, ArticleRepository articleRepository) {
		super();
		this.commandeFournisseurRepository = commandeFournisseurRepository;
		this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
		this.fournisseurRepository = fournisseurRepository;
		this.articleRepository = articleRepository;
	}

	@Override
	public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {

		List<String> error = CommandeFournisseurValidator.validate(commandeFournisseurDto);

		if (!error.isEmpty()) {
			log.error("la commande fournisseur n est pas valide");
			throw new InvalidEntityException("la commande fournisseur est invalide",
					ErrorsCodes.COMMANDE_FOURNISSEUR_NOT_VALID, error);
		}

		Optional<Fournisseur> fournisseur = fournisseurRepository
				.findById(commandeFournisseurDto.getFournisseur().getId());

		if (fournisseur.isEmpty()) {
			log.warn("Aucun fournisseur trouve avec l id " + commandeFournisseurDto.getFournisseur().getId());
			throw new EntitiesNotFoundException(
					"Aucun fournisseur trouve avec l id " + commandeFournisseurDto.getFournisseur().getId(),
					ErrorsCodes.ARTICLE_NOT_FOUND);
		}

		List<String> articleErrors = new ArrayList<>();
		if (commandeFournisseurDto.getLigneCommandeFournisseurs() != null) {
			commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(lignecmd -> {

				if (lignecmd.getArticle() != null) {

					Optional<Article> articleOptional = articleRepository.findById(lignecmd.getArticle().getId());

					if (articleOptional.isEmpty()) {
						articleErrors.add("l'article avec l'id " + lignecmd.getArticle().getId() + " n existe pas");
					}
				} else {
					articleErrors.add("impossible d enregistrer avec un article nul");
				}
			});
		}

		if (!articleErrors.isEmpty()) {
			log.warn("Des articles n existent pas dans la BD");
			throw new InvalidEntityException("certains articles n existent pas dans la BD",
					ErrorsCodes.ARTICLE_NOT_FOUND, articleErrors);
		}

		CommandeFournisseur commandeFournisseur = commandeFournisseurRepository
				.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));

		commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(lignecmd -> {
			LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(lignecmd);
			ligneCommandeFournisseur.setCommandefournisseur(commandeFournisseur);
			ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
		});

		return CommandeFournisseurDto.fromEntity(commandeFournisseur);
	}

	@Override
	public CommandeFournisseurDto findById(Integer id) {

		if (id == null) {
			log.error("Commande fournisseur id nul");
		}
		return commandeFournisseurRepository.findById(id).map(CommandeFournisseurDto::fromEntity).orElseThrow(
				() -> new EntitiesNotFoundException("Aucune commande fournisseur avec l'id " + id + " trouve",
						ErrorsCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));

	}

	@Override
	public CommandeFournisseurDto findByCode(String code) {

		if (StringUtils.hasLength(code)) {
			log.error("Commande Fournisseur Code null");
		}

		return commandeFournisseurRepository.findCommandeFournisseurByCode(code).map(CommandeFournisseurDto::fromEntity)
				.orElseThrow(() -> new EntitiesNotFoundException(
						"Aucune commande fournisseur avec le code " + code + " trouve",
						ErrorsCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Commande fournisseur id nul");
		}
		commandeFournisseurRepository.deleteById(id);
	}

	@Override
	public List<CommandeFournisseurDto> findAll() {

		return commandeFournisseurRepository.findAll().stream().map(CommandeFournisseurDto::fromEntity)
				.collect(Collectors.toList());
	}

}
