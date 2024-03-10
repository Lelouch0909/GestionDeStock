package com.lontsi.gestiondestock.model;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
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
@Table(name = "ventes")
public class Ventes extends AbstractEntity {

	@Column(name = "code")
	private String code;

	@Column(name = "commentaire")
	private String commentaire;

	@Column(name = "datevente")
	private Instant dateVente;

	@Column(name = "identreprise")
	private Integer idEntreprise;

	@OneToMany(mappedBy = "vente")
	private List<LigneVente> ligneVentes;

}
