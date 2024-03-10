package com.lontsi.gestiondestock.dto;

import java.time.Instant;
import java.util.List;

import com.lontsi.gestiondestock.model.CommandeClient;
import com.lontsi.gestiondestock.model.EtatCommande;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommandeClientDto  {

	private String code;
	
	private Instant dateCommande;
	
	  private EtatCommande etatCommande;
	  
	  private Integer idEntreprise;

	
	private ClientDto client;
	
	private List<LigneCommandeClientDto> ligneCommandeClients;

	private Integer id;
	

public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
		
		if(commandeClient==null) {
			return null;
		}
	
	return	CommandeClientDto.builder()
	        .id(commandeClient.getId())
			.client(ClientDto.fromEntity(commandeClient.getClient()))
			.code(commandeClient.getCode())
	        .etatCommande(commandeClient.getEtatCommande())
			.dateCommande(commandeClient.getDateCommande())
	        .idEntreprise(commandeClient.getIdEntreprise())

			.build();
	
		
		
	}
	
	public static CommandeClient toEntity(CommandeClientDto commandeClientDto) {
		if(commandeClientDto==null) {
			return null;
		}
		
		CommandeClient commandeClient = new CommandeClient();
	    commandeClient.setId(commandeClientDto.getId());
		commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClient()));
		commandeClient.setCode(commandeClientDto.getCode());
	    commandeClient.setEtatCommande(commandeClientDto.getEtatCommande());
	    commandeClient.setIdEntreprise(commandeClientDto.getIdEntreprise());
		commandeClient.setDateCommande(commandeClientDto.getDateCommande());

		
		return commandeClient;
	}
}
