package com.lontsi.gestiondestock.controllers.api;

import static com.lontsi.gestiondestock.utils.Constants.APPROOT;

import com.lontsi.gestiondestock.dto.ClientDto;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(APPROOT +"/clients")
public interface ClientApi {

  @PostMapping(value = APPROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  ClientDto save(@RequestBody ClientDto dto);

  @GetMapping(value = APPROOT + "/clients/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
  ClientDto findById(@PathVariable("idClient") Integer id);

  @GetMapping(value = APPROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
  List<ClientDto> findAll();

  @DeleteMapping(value = APPROOT + "/clients/delete/{idClient}")
  void delete(@PathVariable("idClient") Integer id);

}
