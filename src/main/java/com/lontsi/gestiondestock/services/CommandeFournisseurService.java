package com.lontsi.gestiondestock.services;

import java.util.List;

import com.lontsi.gestiondestock.dto.CommandeFournisseurDto;

public interface CommandeFournisseurService {

	CommandeFournisseurDto save(CommandeFournisseurDto commandeClientDto);

	CommandeFournisseurDto findById(Integer id);

	CommandeFournisseurDto findByCode(String code);

	void delete(Integer id);

	List<CommandeFournisseurDto> findAll();

}
