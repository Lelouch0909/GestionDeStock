package com.lontsi.gestiondestock.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lontsi.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import com.lontsi.gestiondestock.dto.UtilisateurDto;
import com.lontsi.gestiondestock.exception.EntitiesNotFoundException;
import com.lontsi.gestiondestock.exception.ErrorsCodes;
import com.lontsi.gestiondestock.exception.InvalidEntityException;
import com.lontsi.gestiondestock.exception.InvalidOperationException;
import com.lontsi.gestiondestock.model.Utilisateur;
import com.lontsi.gestiondestock.repository.UtilisateurRepository;
import com.lontsi.gestiondestock.services.UtilisateurService;
import com.lontsi.gestiondestock.validator.UtilisateurValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	private UtilisateurRepository utilisateurRepository; 
	
	@Override
	public void delete(Integer id) {

		if (id==null) {
			log.error("Id utilisateur est null");
			}
		
		utilisateurRepository.deleteById(id);
	}

	@Override
	public UtilisateurDto save(UtilisateurDto utilisateurDto) {

		List<String> errors = UtilisateurValidator.validate(utilisateurDto);
		
		if (!errors.isEmpty()) {
			log.error("utilisateur non valide", utilisateurDto);
			throw new InvalidEntityException("l'utilisateur n est pas valide",ErrorsCodes.UTILISATEUR_NOT_FOUND,errors);
			}
		
		return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(utilisateurDto)));
	
	}

	@Override
	public UtilisateurDto findById(Integer id) {

		if (id==null) {
			log.error("id de l utilisateur est nul");
			return null;
		}
		
		Optional<Utilisateur> utilisateur =  utilisateurRepository.findById(id);
		UtilisateurDto utilisateurDto = UtilisateurDto.fromEntity(utilisateur.get());
		
		return Optional.of(utilisateurDto)
				.orElseThrow(() -> new EntitiesNotFoundException("utilisateur non trouve avec l'id "+id,ErrorsCodes.UTILISATEUR_NOT_FOUND));
}

	

	@Override
	public List<UtilisateurDto> findAll() {
		return utilisateurRepository.findAll().stream().map(UtilisateurDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto changerMotDePasseUtilisateurDto) {
		validate(changerMotDePasseUtilisateurDto);
		
		Optional<Utilisateur> utilisateur = utilisateurRepository.findById(changerMotDePasseUtilisateurDto.getId());
		
		if (!utilisateur.isEmpty()) {
			log.error("aucun utilisateur trouve avec l'id " +utilisateur.get().getId());
			throw new InvalidEntityException("aucun utilisateur trouve avec l'id",ErrorsCodes.UTILISATEUR_NOT_FOUND);
			}

		Utilisateur utilisateur2 = utilisateur.get();

		utilisateur2.setMotDePasse(changerMotDePasseUtilisateurDto.getMotDePasse());
		
		return UtilisateurDto.fromEntity(utilisateurRepository.save(utilisateur2));
	
	}

	private void validate(ChangerMotDePasseUtilisateurDto dto){
		if (dto == null) {
			log.warn("Impossible de modifier le mot de passe avec un objet NULL");
			throw new InvalidOperationException("Aucune information n'a ete fourni pour pouvoir changer le mot de passe",
				ErrorsCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
		  }
		  if (dto.getId() == null) {
			log.warn("Impossible de modifier le mot de passe avec un ID NULL");
			throw new InvalidOperationException("ID utilisateur null:: Impossible de modifier le mote de passe",
				ErrorsCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
		  }
		  if (!StringUtils.hasLength(dto.getMotDePasse()) || !StringUtils.hasLength(dto.getConfirmerMotDePasse())) {
			log.warn("Impossible de modifier le mot de passe avec un mot de passe NULL");
			throw new InvalidOperationException("Mot de passe utilisateur null:: Impossible de modifier le mote de passe",
				ErrorsCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
		  }
		  if (!dto.getMotDePasse().equals(dto.getConfirmerMotDePasse())) {
			log.warn("Impossible de modifier le mot de passe avec deux mots de passe different");
			throw new InvalidOperationException("Mots de passe utilisateur non conformes:: Impossible de modifier le mote de passe",
				ErrorsCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
		  }
	}
}