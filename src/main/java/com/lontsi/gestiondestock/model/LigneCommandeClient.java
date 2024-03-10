package com.lontsi.gestiondestock.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="lignecommandeclient")
public class LigneCommandeClient  extends AbstractEntity{

	@ManyToOne
	@JoinColumn(name = "idarticle")
	private Article article;
	
	@ManyToOne
	@JoinColumn(name="idcommandeclient")
	private CommandeClient commandeclient;
	
	@Column(name = "quantite")
	private BigDecimal quantite;


	  @Column(name = "identreprise")
	  private Integer idEntreprise;

	@Column(name = "prixunitaire")
	private BigDecimal prixUnitaire;
	
}
