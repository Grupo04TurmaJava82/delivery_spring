package com.generation.delivery.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "tb_produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	@NotNull(message = "Erro: Nome é obrigatório")
	private String nome;
	
	@Column(length = 500)
	private String descricao;

	@Column(precision=6, scale=2)
	@NotNull(message = "Erro: Preço é obrigatório")
	@Positive(message = "Erro: Preço deve ser maior do que zero")
	private BigDecimal preco;

	@Column(length = 5000)
	private String foto;
	
	@Positive(message = "Erro: Calorias deve ser maior do que zero")
	private Integer calorias;
	
	@NotNull(message = "Erro: Nutriscore é obrigatório")
	@Positive(message = "Erro: Nutriscore deve ser maior do que zero")
	private Integer nutriscore;

	/*@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;*/
	
	/*@ManyToOne
	@JsonIgnoreProperties("usuario")
	private Usuario usuario;*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public Integer getCalorias() {
		return this.calorias;
	}

	public void setCalorias(Integer calorias) {
		this.calorias = calorias;
	}

	public Integer getNutriscore() {
		return nutriscore;
	}

	public void setNutriscore(Integer nutriscore) {
		this.nutriscore = nutriscore;
	}
	
	/*public Categoria getCategoria() {
	return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}*/
	
}