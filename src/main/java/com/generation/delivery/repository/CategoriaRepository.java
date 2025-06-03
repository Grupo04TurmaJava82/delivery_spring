package com.generation.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.delivery.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	public List<Categoria> findByNomeContainingIgnoreCase(String nome);
}
