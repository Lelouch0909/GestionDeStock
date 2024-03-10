package com.lontsi.gestiondestock.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lontsi.gestiondestock.dto.CommandeClientDto;
import com.lontsi.gestiondestock.dto.LigneCommandeClientDto;
import com.lontsi.gestiondestock.exception.EntitiesNotFoundException;
import com.lontsi.gestiondestock.exception.ErrorsCodes;
import com.lontsi.gestiondestock.exception.InvalidEntityException;
import com.lontsi.gestiondestock.model.Article;
import com.lontsi.gestiondestock.model.Client;
import com.lontsi.gestiondestock.model.CommandeClient;
import com.lontsi.gestiondestock.model.LigneCommandeClient;
import com.lontsi.gestiondestock.repository.ArticleRepository;
import com.lontsi.gestiondestock.repository.ClientRepository;
import com.lontsi.gestiondestock.repository.CommandeClientRepository;
import com.lontsi.gestiondestock.repository.LigneCommandeClientRepository;
import com.lontsi.gestiondestock.services.CommandeClientService;
import com.lontsi.gestiondestock.validator.CommandeClientValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommandeClientServiceImpl implements CommandeClientService {

	private CommandeClientRepository commandeClientRepository;
	private ClientRepository clientRepository;
	private ArticleRepository articleRepository;
	private LigneCommandeClientRepository ligneCommandeClientRepository;

	public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
			ClientRepository clientRepository, ArticleRepository articleRepository,
			LigneCommandeClientRepository ligneCommandeClientRepository) {
		super();
		this.commandeClientRepository = commandeClientRepository;
		this.clientRepository = clientRepository;
		this.articleRepository = articleRepository;
		this.ligneCommandeClientRepository = ligneCommandeClientRepository;
	}

	@Override
	public CommandeClientDto save(CommandeClientDto commandeClientDto) {

		List<String> errorStrings = CommandeClientValidator.validate(commandeClientDto);
		if (!errorStrings.isEmpty()) {
			log.error("Commande client n'est pas valide");

			throw new InvalidEntityException("commande client non valide", ErrorsCodes.COMMANDE_CLIENT_NOT_VALID,
					errorStrings);
		}

		Optional<Client> client = clientRepository.findById(commandeClientDto.getClient().getId());
		if (client.isEmpty()) {
			log.warn("Client non trouve avec cet ID {} dans la BD", commandeClientDto.getClient().getId());
			throw new EntitiesNotFoundException(
					"Aucun client avec l'id " + commandeClientDto.getClient().getId() + "n a ete trouve",
					ErrorsCodes.CLIENT_NOT_FOUND);
		}

		List<String> articleError = new ArrayList<>();
		if (commandeClientDto.getLigneCommandeClients() != null) {
			commandeClientDto.getLigneCommandeClients().forEach(lignecomcl -> {

				if (lignecomcl.getArticle() != null) {
					Optional<Article> article = articleRepository.findById(lignecomcl.getArticle().getId());
					if (article.isEmpty()) {
						articleError.add("l'article avec l'id " + lignecomcl.getArticle().getId() + " n existe pas");
					}
				} else {
					articleError.add("impossible d enregistrer avec un article nul");
				}
			});

		}
		if (!articleError.isEmpty()) {
			log.warn("Des articles n existent pas dans la BDD");
			throw new InvalidEntityException("Article n existe pas dans la BDD", ErrorsCodes.ARTICLE_NOT_FOUND,
					articleError);
		}

		CommandeClient commandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));

		commandeClientDto.getLigneCommandeClients().forEach(lignecomcl -> {
			LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(lignecomcl);
			ligneCommandeClient.setCommandeclient(commandeClient);
			ligneCommandeClientRepository.save(ligneCommandeClient);
		});
		return CommandeClientDto.fromEntity(commandeClient);
	}

	@Override
	public CommandeClientDto findById(Integer id) {
		if (id == null) {
			log.error("Commande client ID est null");
		}
		return commandeClientRepository.findById(id).map(CommandeClientDto::fromEntity)
				.orElseThrow(() -> new EntitiesNotFoundException("Aucune commande n a ete trouve avec l id " + id,
						ErrorsCodes.COMMANDE_CLIENT_NOT_FOUND));
	}

	@Override
	public CommandeClientDto findByCode(String code) {
		if (StringUtils.hasLength(code)) {
			log.error("Commande client code est null");
		}
		return commandeClientRepository.findCommandeClientByCode(code).map(CommandeClientDto::fromEntity)
				.orElseThrow(() -> new EntitiesNotFoundException("Aucune commande n a ete trouve avec le code " + code,
						ErrorsCodes.COMMANDE_CLIENT_NOT_FOUND));
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Commande client ID est null");
		}
		commandeClientRepository.deleteById(id);
	}

	@Override
	public List<CommandeClientDto> findAll() {
		return commandeClientRepository.findAll().stream().map(CommandeClientDto::fromEntity)
				.collect(Collectors.toList());
	}

}
