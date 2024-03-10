package com.lontsi.gestiondestock.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lontsi.gestiondestock.dto.ClientDto;
import com.lontsi.gestiondestock.exception.EntitiesNotFoundException;
import com.lontsi.gestiondestock.exception.ErrorsCodes;
import com.lontsi.gestiondestock.exception.InvalidEntityException;
import com.lontsi.gestiondestock.exception.InvalidOperationException;
import com.lontsi.gestiondestock.model.CommandeClient;
import com.lontsi.gestiondestock.repository.ClientRepository;
import com.lontsi.gestiondestock.repository.CommandeClientRepository;
import com.lontsi.gestiondestock.services.ClientService;
import com.lontsi.gestiondestock.validator.ClientValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

	private ClientRepository clientRepository;
	private CommandeClientRepository commandeClientRepository;

	public ClientServiceImpl(ClientRepository clientRepository, CommandeClientRepository commandeClientRepository) {
		this.clientRepository = clientRepository;
		this.commandeClientRepository = commandeClientRepository;
	}

	@Override
	public ClientDto save(ClientDto dto) {
		List<String> errors = ClientValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Client is not valid {}", dto);
			throw new InvalidEntityException("Le client n'est pas valide", ErrorsCodes.CLIENT_NOT_FOUND, errors);
		}

		return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(dto)));
	}

	@Override
	public ClientDto findById(Integer id) {
		if (id == null) {
			log.error("Client ID is null");
			return null;
		}
		return clientRepository.findById(id).map(ClientDto::fromEntity).orElseThrow(() -> new EntitiesNotFoundException(
				"Aucun Client avec l'ID = " + id + " n' ete trouve dans la BDD", ErrorsCodes.CLIENT_NOT_FOUND));
	}

	@Override
	public List<ClientDto> findAll() {
		return clientRepository.findAll().stream().map(ClientDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Client ID is null");
			return;
		}
		List<CommandeClient> commandeClients = commandeClientRepository.findAllByClientId(id);
		if (!commandeClients.isEmpty()) {
			throw new InvalidOperationException("Impossible de supprimer un client qui a deja des commande clients",
					ErrorsCodes.CLIENT_ALREADY_IN_USE);
		}
		clientRepository.deleteById(id);
	}
}
