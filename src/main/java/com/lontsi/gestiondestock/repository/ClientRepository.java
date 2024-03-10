package com.lontsi.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lontsi.gestiondestock.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
