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
import jakarta.validation.constraints.PositiveOrZero;

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

	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Categoria categoria;
	
	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Usuario usuario;
	
	private Integer nutriscore;
	//atributos para o cálculo do Nutriscore
	@NotNull(message = "Erro: Energia é obrigatório")
	@Positive(message = "Erro: Energia deve ser maior do que zero")
	private Double energia;                //kJ
	
	@NotNull(message = "Erro: Açucares é obrigatório")
	@PositiveOrZero(message = "Erro: Açucares deve ser maior ou igual a zero")
	private Double acucares;               //g
	
	@NotNull(message = "Erro: Gorduras Saturadas é obrigatório")
	@PositiveOrZero(message = "Erro: Gorduras Saturadas deve ser maior ou igual a zero")
	private Double gordurasSaturadas;      //g
	
	@NotNull(message = "Erro: Sódio é obrigatório")
	@PositiveOrZero(message = "Erro: Sódio deve ser maior ou igual a zero")
	private Double sodio;                  //mg
	
	@NotNull(message = "Erro: Frutas é obrigatório")
	@PositiveOrZero(message = "Erro: Frutas deve ser maior ou igual a zero")
	private Double frutas;                 //%
	
	@NotNull(message = "Erro: Fibras é obrigatório")
	@PositiveOrZero(message = "Erro: Fibras deve ser maior ou igual a zero")
	private Double fibras;                 //g
	
	@NotNull(message = "Erro: Proteínas é obrigatório")
	@PositiveOrZero(message = "Erro: Proteínas deve ser maior ou igual a zero")
	private Double proteinas;              //g

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
	
	public Categoria getCategoria() {
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
	}

	
	//Getters e Setters dos atributos Nutriscore
	public Double getEnergia() {
		return energia;
	}

	public void setEnergia(Double energia) {
		this.energia = energia;
	}

	public Double getAcucares() {
		return acucares;
	}

	public void setAcucares(Double acucares) {
		this.acucares = acucares;
	}

	public Double getGordurasSaturadas() {
		return gordurasSaturadas;
	}

	public void setGordurasSaturadas(Double gordurasSaturadas) {
		this.gordurasSaturadas = gordurasSaturadas;
	}

	public Double getSodio() {
		return sodio;
	}

	public void setSodio(Double sodio) {
		this.sodio = sodio;
	}

	public Double getFibras() {
		return fibras;
	}

	public void setFibras(Double fibras) {
		this.fibras = fibras;
	}

	public Double getProteinas() {
		return proteinas;
	}

	public void setProteinas(Double proteinas) {
		this.proteinas = proteinas;
	}

	public Double getFrutas() {
		return frutas;
	}

	public void setFrutas(Double frutas) {
		this.frutas = frutas;
	}
	
}