package com.lontsi.gestiondestock.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name="entreprise")
public class Entreprise  extends AbstractEntity{

	@Column(name = "nom")
	private String nom;
	


	@Column(name = "description")
	private String description;
	
	@Column(name = "photo")
	private String photo;
	
	@Column(name = "codefiscal")
	private String codeFiscal;
	
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "numtel")
	private String numTel;
	
	@Column(name = "siteweb")
	private String siteWeb;
	
	
	@Embedded
	private Adresse adresse;
	
	@OneToMany(mappedBy = "entreprise")
	private List<Utilisateur> utilisateur;

}
