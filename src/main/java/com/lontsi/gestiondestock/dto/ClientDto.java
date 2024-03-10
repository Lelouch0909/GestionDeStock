package com.lontsi.gestiondestock.dto;

import java.util.List;

import com.lontsi.gestiondestock.model.Client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto  {

	private String nom;
	
	private String prenom;
	
	
	private String photo;
	
	private String mail;
	
	private String numTel;
	
	private AdresseDto adresse;
	
	private List<CommandeClientDto> commandeClient;
	
	  private Integer idEntreprise;

	private Integer id;
	
	
	
	
public static ClientDto fromEntity(Client client) {
		
		if(client==null) {
			return null;
		}
	
	return	ClientDto.builder()
	        .id(client.getId())
	        .adresse(AdresseDto.fromEntity(client.getAdresse()))
			.nom(client.getNom())
			.prenom(client.getPrenom())
			.photo(client.getPhoto())
			.mail(client.getMail())
			.numTel(client.getNumTel())
	        .idEntreprise(client.getIdEntreprise())
			.build();
	
		
		
	}
	
	public static Client toEntity(ClientDto clientDto) {
		if(clientDto==null) {
			return null;
		}
		
		Client client = new Client();
		client.setAdresse(AdresseDto.toEntity(clientDto.getAdresse()));
		client.setNom(clientDto.getNom());
		client.setPrenom(clientDto.getPrenom());
		client.setPhoto(clientDto.getPhoto());
		client.setMail(clientDto.getMail());
		client.setNumTel(clientDto.getNumTel());
	    client.setIdEntreprise(clientDto.getIdEntreprise());
	    client.setId(clientDto.getId());

		
		return client;
	}
}
