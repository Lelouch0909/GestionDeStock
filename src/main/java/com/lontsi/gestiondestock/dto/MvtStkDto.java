package com.lontsi.gestiondestock.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.lontsi.gestiondestock.model.MvtStk;
import com.lontsi.gestiondestock.model.SourceMvtStk;
import com.lontsi.gestiondestock.model.TypeMvtStk;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MvtStkDto  {


	private ArticleDto article;
	
	
	private Instant dateMvt;

	private BigDecimal quantite;

	private TypeMvtStk typeMvt;

	private Integer id;

	  private SourceMvtStk sourceMvt;

	  private Integer idEntreprise;
	
	

public static MvtStkDto fromEntity(MvtStk mvtStk) {
		
		if(mvtStk==null) {
			return null;
		}
	
	return	MvtStkDto.builder()
	        .id(mvtStk.getId())
			.article(ArticleDto.fromEntity(mvtStk.getArticle()))
			.typeMvt(mvtStk.getTypeMvt())
			.dateMvt(mvtStk.getDateMvt())
			.quantite(mvtStk.getQuantite())
	        .sourceMvt(mvtStk.getSourceMvt())
		    .idEntreprise(mvtStk.getIdEntreprise())
		       
			.build();
	
		
		
	}
	
	public static  MvtStk toEntity(MvtStkDto  dto) {
		if(dto==null) {
			return null;
		}
		

	    MvtStk mvtStk = new MvtStk();
	    
	    mvtStk.setId(dto.getId());
	    mvtStk.setDateMvt(dto.getDateMvt());
	    mvtStk.setQuantite(dto.getQuantite());
	    mvtStk.setArticle(ArticleDto.toEntity(dto.getArticle()));
	    mvtStk.setTypeMvt(dto.getTypeMvt());
	    mvtStk.setSourceMvt(dto.getSourceMvt());
	    mvtStk.setIdEntreprise(dto.getIdEntreprise());
	    return mvtStk;
	}
}
