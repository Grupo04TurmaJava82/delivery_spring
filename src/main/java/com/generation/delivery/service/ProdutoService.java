package com.generation.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.delivery.model.Produto;
import com.generation.delivery.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
    @Autowired
    private ProdutoRepository produtoRepository;
    
    //Arrays com valores padrão de nutrientes
    private double[] valoresEnergia = {335, 670, 1005, 1340, 1675, 2010, 2345, 2680, 3015, 3350};
    private double[] valoresAcucar = {4.5, 9, 13.5, 18, 22.5, 27, 31, 36, 40, 45};
    private double[] valoresGordurasSat = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private double[] valoresSodio = {90, 180, 270, 360, 450, 540, 630, 720, 810, 900};
    private double[] valoresFrutas = {40, 60, 80};
    private double[] valoresFibra = {0.9, 1.9, 2.8, 3.7, 4.7};
    private double[] valoresProteina = {1.6, 3.2, 4.8, 6.4, 8.0};
    
	
    public Integer calulcarPontosNegativos(Produto produto) {
		int pontosEnergia = getPontos(produto.getEnergia(), valoresEnergia);
        int pontosAcucar = getPontos(produto.getAcucares(), valoresAcucar);
        int pontosGortudaSat = getPontos(produto.getGordurasSaturadas(), valoresGordurasSat);
        int pontosSodio = getPontos(produto.getSodio(), valoresSodio);

        return pontosEnergia + pontosAcucar + pontosGortudaSat + pontosSodio;
    }
    
    public Integer calulcarPontosPositivos(Produto produto) {
    	int pontosFrutas = getPontos(produto.getFrutas(), valoresFrutas);
        int pontosFibras = getPontos(produto.getFibras(), valoresFibra);
        int pontosProteinas = getPontos(produto.getProteinas(), valoresProteina);
        
        int pontosNegativos = calulcarPontosNegativos(produto);
        if (pontosNegativos >= 11 && pontosFrutas < 5) {
            // só conta frutas e fibras
           return pontosFrutas + pontosFibras;
        } else {
            // conta tudo
        	return pontosFrutas + pontosFibras + pontosProteinas;
        }
    }
    
    public Integer calcularNutriscore(Produto produto) {
    	int pontosNegativos = calulcarPontosNegativos(produto);
    	int pontosPositivos = calulcarPontosPositivos(produto);
        int score = pontosNegativos - pontosPositivos;
        
        System.out.println(score);
        
        if (score <= -1) return 1;                     //A
        else if (score >= 0 && score <= 2) return 2;   //B
        else if (score >= 3 && score <= 10) return 3;  //C
        else if (score >= 11 && score <= 18) return 4; //D
        else return 5;                                 //E
    }
    
    // Helper para comparar valor com intervalos de pontos
    private Integer getPontos(Double value, double[] arrayValoresNutrientes) {
    	
        for (int i = 0; i < arrayValoresNutrientes.length; i++) {
            if (value <= arrayValoresNutrientes[i]) return i;
        }
        return arrayValoresNutrientes.length;
    }
    
    public Produto cadastrarProdutoComNutriscore(Produto produto) {
    	Integer nutriscore = calcularNutriscore(produto);
        produto.setNutriscore(nutriscore);
        return produtoRepository.save(produto);
    }
}