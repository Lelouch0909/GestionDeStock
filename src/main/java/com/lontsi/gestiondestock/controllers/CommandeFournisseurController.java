package com.lontsi.gestiondestock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.lontsi.gestiondestock.controllers.api.CommandeFournisseurApi;
import com.lontsi.gestiondestock.dto.CommandeFournisseurDto;
import com.lontsi.gestiondestock.services.CommandeFournisseurService;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

    private CommandeFournisseurService commandeFournisseurService;

     @Autowired
    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService){
        this.commandeFournisseurService=commandeFournisseurService;
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto commandeFournisseurDtoDto) {
        return ResponseEntity.ok(commandeFournisseurService.save(commandeFournisseurDtoDto));
     }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findById(Integer IdcommandeFournisseur) {
        return ResponseEntity.ok(commandeFournisseurService.findById(IdcommandeFournisseur));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findByCode(String code) {
        return ResponseEntity.ok(commandeFournisseurService.findByCode(code));
    }

    @Override
    public ResponseEntity delete(Integer id) {
        commandeFournisseurService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
        return ResponseEntity.ok(commandeFournisseurService.findAll());
    }
    
}
