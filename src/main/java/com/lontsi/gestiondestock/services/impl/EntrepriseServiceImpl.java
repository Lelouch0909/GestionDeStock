package com.lontsi.gestiondestock.services.impl;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lontsi.gestiondestock.dto.EntrepriseDto;
import com.lontsi.gestiondestock.dto.RolesDto;
import com.lontsi.gestiondestock.dto.UtilisateurDto;
import com.lontsi.gestiondestock.exception.EntitiesNotFoundException;
import com.lontsi.gestiondestock.exception.ErrorsCodes;
import com.lontsi.gestiondestock.exception.InvalidEntityException;
import com.lontsi.gestiondestock.repository.EntrepriseRepository;
import com.lontsi.gestiondestock.repository.RolesRepository;
import com.lontsi.gestiondestock.services.EntrepriseService;
import com.lontsi.gestiondestock.services.UtilisateurService;
import com.lontsi.gestiondestock.validator.EntrepriseValidator;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

	private EntrepriseRepository entrepriseRepository;
	private UtilisateurService utilisateurService;
	private RolesRepository rolesRepository;

	@Autowired
	public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, UtilisateurService utilisateurService,
			RolesRepository rolesRepository) {
		this.entrepriseRepository = entrepriseRepository;
		this.utilisateurService = utilisateurService;
		this.rolesRepository = rolesRepository;
	}

	@Override
	public EntrepriseDto save(EntrepriseDto dto) {
		List<String> errors = EntrepriseValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Entreprise is not valid {}", dto);
			throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorsCodes.ENTREPRISE_NOT_VALID, errors);
		}
		EntrepriseDto savedEntreprise = EntrepriseDto
				.fromEntity(entrepriseRepository.save(EntrepriseDto.toEntity(dto)));

		UtilisateurDto utilisateur = fromEntreprise(savedEntreprise);

		UtilisateurDto savedUser = utilisateurService.save(utilisateur);

		RolesDto rolesDto = RolesDto.builder().roleName("ADMIN").utilisateur(savedUser).build();

		rolesRepository.save(RolesDto.toEntity(rolesDto));

		return savedEntreprise;
	}

	private UtilisateurDto fromEntreprise(EntrepriseDto dto) {
		return UtilisateurDto.builder().adresse(dto.getAdresse()).nom(dto.getNom()).prenom(dto.getCodeFiscal())
				.email(dto.getEmail()).motDePasse(generateRandomPassword()).entreprise(dto)
				.dateDeNaissance(Instant.now()).photo(dto.getPhoto()).build();
	}

	private String generateRandomPassword() {
		return "som3R@nd0mP@$$word";
	}

	@Override
	public EntrepriseDto findById(Integer id) {
		if (id == null) {
			log.error("Entreprise ID is null");
			return null;
		}
		return entrepriseRepository.findById(id).map(EntrepriseDto::fromEntity)
				.orElseThrow(() -> new EntitiesNotFoundException(
						"Aucune entreprise avec l'ID = " + id + " n' ete trouve dans la BDD",
						ErrorsCodes.ENTREPRISE_NOT_FOUND));
	}

	@Override
	public List<EntrepriseDto> findAll() {
		return entrepriseRepository.findAll().stream().map(EntrepriseDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Entreprise ID is null");
			return;
		}
		entrepriseRepository.deleteById(id);
	}
}
