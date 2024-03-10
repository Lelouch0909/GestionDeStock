package com.lontsi.gestiondestock.dto;

import com.lontsi.gestiondestock.model.Roles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolesDto{

	private String roleName;

	private UtilisateurDto utilisateur;

	private Integer id;
	
	
	public static  RolesDto fromEntity(Roles roles) {
	    if (roles == null) {
	      return null;
	    }
	    return RolesDto.builder()
	        .id(roles.getId())
	        .roleName(roles.getRoleName())
	        .utilisateur(UtilisateurDto.fromEntity(roles.getUtilisateur()))
	        .build();
	  }

	  public static Roles toEntity(RolesDto dto) {
	    if (dto == null) {
	      return null;
	    }
	    Roles roles = new Roles();
	    roles.setId(dto.getId());
	    roles.setRoleName(dto.getRoleName());
	    roles.setUtilisateur(UtilisateurDto.toEntity(dto.getUtilisateur()));
	    return roles;
	  }
}
