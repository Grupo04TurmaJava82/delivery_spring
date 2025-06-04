package com.generation.delivery.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.delivery.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
	
	public List<Produto> findAllByCategoriaId(Integer categoria);
	
	public List<Produto> findAllByCaloriasGreaterThan(Integer calorias); //Calorias maior que
	public List<Produto> findAllByCaloriasLessThan(Integer calorias); //Calorias menor que
	
	public List<Produto> findAllByPrecoGreaterThan(BigDecimal preco);
	public List<Produto> findAllByPrecoLessThan(BigDecimal preco);
	
	//Retorna produtos em um intervalo de Nutriscore
	public List<Produto> findAllByNutriscoreBetween(Integer nsMinimo, Integer nsMaximo);
}