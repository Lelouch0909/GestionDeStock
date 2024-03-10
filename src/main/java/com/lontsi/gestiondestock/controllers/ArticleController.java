package com.lontsi.gestiondestock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.lontsi.gestiondestock.controllers.api.ArticleApi;
import com.lontsi.gestiondestock.dto.ArticleDto;
import com.lontsi.gestiondestock.services.ArticleService;

@RestController
public class ArticleController implements ArticleApi {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @Override
    public void delete(Integer id) {

        articleService.delete(id);
    }

    @Override
    public List<ArticleDto> findAll() {

        return articleService.findAll();
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public ArticleDto findById(Integer id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        return articleService.save(articleDto);
    }

}
