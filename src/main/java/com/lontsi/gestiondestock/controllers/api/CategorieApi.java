package com.lontsi.gestiondestock.controllers.api;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.MediaType;
import static com.lontsi.gestiondestock.utils.Constants.APPROOT;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lontsi.gestiondestock.dto.CategoryDto;

@Api(APPROOT + "/categorie")
public interface CategorieApi {
    

	@DeleteMapping(value = APPROOT +"/categorie/delete/{icategory}")
	@Operation(summary = "supprimer une categorie" )
	@ApiResponses( value = {
		@ApiResponse(responseCode = "200", description = "object categorie supprime")
	})
	void delete(@PathVariable("icategory") Integer id);

	/*--------------------------------------------------------------------------------------------- */

	@Operation(summary = "trouver toutes les categories", description = "cette methode permet de lister toutes les categories")
	@ApiResponses( value = {
		@ApiResponse(responseCode = "200", description = "object article trouver"),
		@ApiResponse(responseCode = "400", description = "objects  non trouver")}
		)
	@GetMapping(value = APPROOT +"/categorie/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<CategoryDto> findAll();

	/*--------------------------------------------------------------------------------------------- */


	@Operation(summary = "trouver une categorie avec son CODE", description = "cette methode permet de trouver la categorie avec son CODE")
	@ApiResponses( value = {
		@ApiResponse(responseCode = "200", description = "object categorie trouver"),
		@ApiResponse(responseCode = "400", description = "object categorie non trouver dans la BDD")
	})
	@GetMapping(value = APPROOT +"/categorie/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
	CategoryDto findByCodeArticle(@PathVariable String codeCategorie);

	/*--------------------------------------------------------------------------------------------- */


	@GetMapping(value = APPROOT +"/categorie/id/{idcategorie}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "trouver une categorie ave l'ID", description = "cette methode permet de trouver la categorie ave l'ID")
	@ApiResponses( value = {
		@ApiResponse(responseCode = "200", description = "object categorie trouver"),
		@ApiResponse(responseCode = "400", description = "object categorie non trouver dans la BDD")
	})
	CategoryDto findById(@PathVariable("idcategorie") Integer id);

	/*--------------------------------------------------------------------------------------------- */

    @PostMapping(value = APPROOT +"/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "enregistrer/modifier une categorie", description = "cette methode permet d'ajouter ou modifier une categorie")
	@ApiResponses( value = {
		@ApiResponse(responseCode = "200", description = "object categorie cree/modifie"),
		@ApiResponse(responseCode = "400", description = "object categorie non valide")
	})
	CategoryDto save(@RequestBody CategoryDto categorieDto);

}
