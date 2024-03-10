package com.lontsi.gestiondestock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lontsi.gestiondestock.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Optional<Category> findByCode(String code);
}
