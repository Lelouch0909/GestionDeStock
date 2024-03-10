package com.lontsi.gestiondestock.services;

import java.util.List;

import com.lontsi.gestiondestock.dto.CommandeClientDto;

public interface CommandeClientService {

	CommandeClientDto save(CommandeClientDto commandeClientDto);
	CommandeClientDto findById(Integer id);
	CommandeClientDto findByCode(String code);
	void delete(Integer id);
	List<CommandeClientDto> findAll();
	
}
