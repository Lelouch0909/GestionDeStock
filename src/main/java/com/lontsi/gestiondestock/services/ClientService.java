package com.lontsi.gestiondestock.services;

import java.util.List;

import com.lontsi.gestiondestock.dto.ClientDto;

public interface ClientService {

  ClientDto save(ClientDto dto);

  ClientDto findById(Integer id);

  List<ClientDto> findAll();

  void delete(Integer id);

}
