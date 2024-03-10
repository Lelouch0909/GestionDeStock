package com.lontsi.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.lontsi.gestiondestock.dto.ClientDto;

public class ClientValidator {

	public static List<String> validate(ClientDto clientDto) {
	List<String> errors = new ArrayList<>();
	
	if (clientDto==null||!StringUtils.hasLength(clientDto.getNom())) {
		errors.add("veuillez renseigner le nom");
	}
	
	if (clientDto==null||!StringUtils.hasLength(clientDto.getPrenom())) {
		errors.add("veuillez renseigner le prenom");
	}
	if (clientDto==null||!StringUtils.hasLength(clientDto.getPhoto())) {
		errors.add("veuillez ajouter une photo");
	}
	
	if (clientDto==null||clientDto.getNumTel()==null) {
		errors.add("veuillez renseigner le numero de tel");
	}
	
	if (clientDto==null||clientDto.getMail()==null) {
		errors.add("veuillez renseigner le mail");
	}
	
	
	return errors;		
}
}