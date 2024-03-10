package com.lontsi.gestiondestock.controllers.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lontsi.gestiondestock.dto.CommandeClientDto;

import io.swagger.annotations.Api;
import static com.lontsi.gestiondestock.utils.Constants.APPROOT;

@Api(APPROOT +"/commandesclients")
public interface CommandeClientApi {
    
    @PostMapping(APPROOT+ "/commandesclients/create")
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto commandeClientDto);

    @GetMapping(APPROOT+ "/commandesclients/idCommandeClient")
	ResponseEntity<CommandeClientDto> findById(@PathVariable Integer idCommandeClient);
	
    @GetMapping(APPROOT+ "/commandesclients/{codeCommandeClient}")
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient")String code);
	
    @DeleteMapping(APPROOT+ "/commandesclients/delete/{id}")
    ResponseEntity delete(@PathVariable Integer id);
	
    @GetMapping(APPROOT+ "/commandesclients/all")
    ResponseEntity<List<CommandeClientDto>> findAll();
	
}
