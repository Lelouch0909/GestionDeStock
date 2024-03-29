package com.lontsi.gestiondestock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.lontsi.gestiondestock.controllers.api.CommandeClientApi;
import com.lontsi.gestiondestock.dto.CommandeClientDto;
import com.lontsi.gestiondestock.services.CommandeClientService;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private CommandeClientService commandeClientService;

    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService){
        this.commandeClientService=commandeClientService;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto commandeClientDto) {
        return ResponseEntity.ok(commandeClientService.save(commandeClientDto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Integer id) {
        return ResponseEntity.ok(commandeClientService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        return ResponseEntity.ok(commandeClientService.findByCode(code));
    }

    @Override
    public ResponseEntity delete(Integer id) {
        commandeClientService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }
    
}