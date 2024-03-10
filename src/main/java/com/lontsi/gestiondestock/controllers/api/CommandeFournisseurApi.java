package com.lontsi.gestiondestock.controllers.api;

import io.swagger.annotations.Api;
import static com.lontsi.gestiondestock.utils.Constants.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lontsi.gestiondestock.dto.CommandeFournisseurDto;

@Api(COMMANDE_FOURNISSEUR_ENDPOINT)
public interface CommandeFournisseurApi {

    @PostMapping(COMMANDE_FOURNISSEUR_ENDPOINT+ "/create")
    ResponseEntity<CommandeFournisseurDto> save(@RequestBody CommandeFournisseurDto commandeFournisseurDtoDto);

    @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT+"/{IdCommandFournisseur}")
	ResponseEntity<CommandeFournisseurDto> findById(@PathVariable Integer IdCommandeFournisseur);
	
    @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT+"/{codeCommandeFournisseur}")
    ResponseEntity<CommandeFournisseurDto> findByCode(@PathVariable("codeCommandeFournisseur")String code);
	
    @DeleteMapping(COMMANDE_FOURNISSEUR_ENDPOINT+"/delete/{id}")
    ResponseEntity delete(@PathVariable Integer id);
	
    @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT+"/all")
    ResponseEntity<List<CommandeFournisseurDto>> findAll();
	
}
