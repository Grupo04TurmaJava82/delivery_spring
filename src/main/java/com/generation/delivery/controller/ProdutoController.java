package com.generation.delivery.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.delivery.model.Produto;
import com.generation.delivery.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto){
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto){
		
		if(produto.getId() == null)
			return ResponseEntity.badRequest().build();
		
		if(produtoRepository.existsById(produto.getId()))
			return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if(produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		produtoRepository.deleteById(id);
	}
	
	// Métodos específicos
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	/*
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity<List<Produto>> getByCategoria(@PathVariable String categoria){
		return ResponseEntity.ok(produtoRepository.findAllByCategoriaContainingIgnoreCase(categoria));
	}
	*/
	
	@GetMapping("/nutriscore")
	public ResponseEntity<List<Produto>> getByNutriscore(@RequestParam Integer nsMinimo, @RequestParam Integer nsMaximo) {
	    return ResponseEntity.ok(produtoRepository.findAllByNutriscoreBetween(nsMinimo, nsMaximo));
	}
	
	@GetMapping("/caloriasMaioresQue/{calorias}")
	public ResponseEntity<List<Produto>> getByCaloriasGreaterThan(@PathVariable Integer calorias) {
	    return ResponseEntity.ok(produtoRepository.findAllByCaloriasGreaterThan(calorias));
	}

	@GetMapping("/caloriasMenoresQue/{calorias}")
	public ResponseEntity<List<Produto>> getByCaloriasLessThan(@PathVariable Integer calorias) {
	    return ResponseEntity.ok(produtoRepository.findAllByCaloriasLessThan(calorias));
	}
	
	@GetMapping("/precoMaiorQue/{preco}")
	public ResponseEntity<List<Produto>> getByPrecoGreaterThan(@PathVariable BigDecimal preco) {
	    return ResponseEntity.ok(produtoRepository.findAllByPrecoGreaterThan(preco));
	}

	@GetMapping("/precoMenorQue/{preco}")
	public ResponseEntity<List<Produto>> getByPrecoLessThan(@PathVariable BigDecimal preco) {
	    return ResponseEntity.ok(produtoRepository.findAllByPrecoLessThan(preco));
	}
}
