package com.lontsi.gestiondestock.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lontsi.gestiondestock.dto.FournisseurDto;
import com.lontsi.gestiondestock.exception.EntitiesNotFoundException;
import com.lontsi.gestiondestock.exception.ErrorsCodes;
import com.lontsi.gestiondestock.exception.InvalidEntityException;
import com.lontsi.gestiondestock.exception.InvalidOperationException;
import com.lontsi.gestiondestock.model.CommandeClient;
import com.lontsi.gestiondestock.repository.CommandeFournisseurRepository;
import com.lontsi.gestiondestock.repository.FournisseurRepository;
import com.lontsi.gestiondestock.services.FournisseurService;
import com.lontsi.gestiondestock.validator.FournisseurValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

	private FournisseurRepository fournisseurRepository;
	private CommandeFournisseurRepository commandeFournisseurRepository;

	@Autowired
	public FournisseurServiceImpl(FournisseurRepository fournisseurRepository,
			CommandeFournisseurRepository commandeFournisseurRepository) {
		this.fournisseurRepository = fournisseurRepository;
		this.commandeFournisseurRepository = commandeFournisseurRepository;
	}

	@Override
	public FournisseurDto save(FournisseurDto dto) {
		List<String> errors = FournisseurValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Fournisseur is not valid {}", dto);
			throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorsCodes.FOURNISSEUR_NOT_VALID,
					errors);
		}

		return FournisseurDto.fromEntity(fournisseurRepository.save(FournisseurDto.toEntity(dto)));
	}

	@Override
	public FournisseurDto findById(Integer id) {
		if (id == null) {
			log.error("Fournisseur ID is null");
			return null;
		}
		return fournisseurRepository.findById(id).map(FournisseurDto::fromEntity)
				.orElseThrow(() -> new EntitiesNotFoundException(
						"Aucun fournisseur avec l'ID = " + id + " n' ete trouve dans la BDD",
						ErrorsCodes.FOURNISSEUR_NOT_FOUND));
	}

	@Override
	public List<FournisseurDto> findAll() {
		return fournisseurRepository.findAll().stream().map(FournisseurDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Fournisseur ID is null");
			return;
		}
		List<CommandeClient> commandeFournisseur = commandeFournisseurRepository.findAllByFournisseurId(id);
		if (!commandeFournisseur.isEmpty()) {
			throw new InvalidOperationException("Impossible de supprimer un fournisseur qui a deja des commandes",
					ErrorsCodes.FOURNISSEUR_ALREADY_IN_USE);
		}
		fournisseurRepository.deleteById(id);
	}
}
