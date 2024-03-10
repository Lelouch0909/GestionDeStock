package com.lontsi.gestiondestock.controllers.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lontsi.gestiondestock.dto.ArticleDto;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static com.lontsi.gestiondestock.utils.Constants.APPROOT;

@Api(APPROOT + "/articles")
public interface ArticleApi {

	
	@DeleteMapping(value = APPROOT +"/articles/delete/{idarticle}")
	@Operation(summary = "supprimer un article" )
	@ApiResponses( value = {
		@ApiResponse(responseCode = "200", description = "object article supprime")
	})
	void delete(@PathVariable("idarticle") Integer id);

	/*--------------------------------------------------------------------------------------------- */

	@Operation(summary = "trouver tous les articles", description = "cette methode permet de lister toutes les categorie")
	@ApiResponses( value = {
		@ApiResponse(responseCode = "200", description = "object categorie trouver"),
		@ApiResponse(responseCode = "400", description = "object categorie  non trouver")}
		)
	@GetMapping(value = APPROOT +"/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<ArticleDto> findAll();

	/*--------------------------------------------------------------------------------------------- */


	@Operation(summary = "trouver une categorie avec son CODE", description = "cette methode permet de trouver la categorie avec le CODE")
	@ApiResponses( value = {
		@ApiResponse(responseCode = "200", description = "object article trouver"),
		@ApiResponse(responseCode = "400", description = "object article non trouver dans la BDD")
	})
	@GetMapping(value = APPROOT +"/articles/{codeCategorie}", produces = MediaType.APPLICATION_JSON_VALUE)
	ArticleDto findByCodeArticle(@PathVariable String codeCategorie);

	/*--------------------------------------------------------------------------------------------- */


	@GetMapping(value = APPROOT +"/articles/{idarticle}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "trouver un article ave l'ID", description = "cette methode permet de trouver l artile ave l'ID")
	@ApiResponses( value = {
		@ApiResponse(responseCode = "200", description = "object article trouver"),
		@ApiResponse(responseCode = "400", description = "object article non trouver dans la BDD")
	})
	ArticleDto findById(@PathVariable("idarticle") Integer id);

	/*--------------------------------------------------------------------------------------------- */

    @PostMapping(value = APPROOT +"/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "enregistrer/modifier un article", description = "cette methode permet d'ajouter ou modifier un article")
	@ApiResponses( value = {
		@ApiResponse(responseCode = "200", description = "object article cree/modifie"),
		@ApiResponse(responseCode = "400", description = "object article non valide")
	})
	ArticleDto save(ArticleDto articleDto);

}
