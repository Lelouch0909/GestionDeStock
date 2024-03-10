package com.lontsi.gestiondestock.dto;

import java.time.Instant;
import java.util.List;

import com.lontsi.gestiondestock.model.Ventes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class VentesDto {

	private String code;

	private String commentaire;

	private Instant dateVente;

	private List<LigneVenteDto> ligneVentes;

	private Integer id;
	private Integer idEntreprise;

	public static VentesDto fromEntity(Ventes ventes) {

		if (ventes == null) {
			return null;
		}

		return VentesDto.builder().id(ventes.getId()).commentaire(ventes.getCommentaire())
				.idEntreprise(ventes.getIdEntreprise()).code(ventes.getCode()).dateVente(ventes.getDateVente()).build();

	}

	public static Ventes toEntity(VentesDto ventesDto) {
		if (ventesDto == null) {
			return null;
		}

		Ventes ventes = new Ventes();
		ventes.setId(ventesDto.getId());
		ventes.setIdEntreprise(ventesDto.getIdEntreprise());
		ventes.setCommentaire(ventesDto.getCommentaire());
		ventes.setCode(ventesDto.getCode());
		ventes.setDateVente(ventesDto.getDateVente());

		return ventes;
	}
}
