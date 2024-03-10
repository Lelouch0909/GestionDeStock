package com.lontsi.gestiondestock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.lontsi.gestiondestock.controllers.api.CategorieApi;
import com.lontsi.gestiondestock.dto.CategoryDto;
import com.lontsi.gestiondestock.services.CategoryService;

@RestController
public class CategoryController implements CategorieApi{

    
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Override
    public void delete(Integer id) {
        categoryService.delete(id);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public CategoryDto findByCodeArticle(String codeArticle) {
        return categoryService.findByCode(codeArticle);
    }

    @Override
    public CategoryDto findById(Integer id) {
        return categoryService.findById(id);
    }

    @Override
    public CategoryDto save(CategoryDto categorieDto) {
        return categoryService.save(categorieDto);
    }
    
}
