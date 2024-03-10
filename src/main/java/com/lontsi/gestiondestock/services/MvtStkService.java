package com.lontsi.gestiondestock.services;

import java.math.BigDecimal;
import java.util.List;

import com.lontsi.gestiondestock.dto.MvtStkDto;

public interface MvtStkService {

	BigDecimal stockReelArticle(Integer idArticle);

	List<MvtStkDto> mvtStkArticle(Integer idArticle);

	MvtStkDto entreeStock(MvtStkDto dto);

	MvtStkDto sortieStock(MvtStkDto dto);

	MvtStkDto correctionStockPos(MvtStkDto dto);

	MvtStkDto correctionStockNeg(MvtStkDto dto);

}
