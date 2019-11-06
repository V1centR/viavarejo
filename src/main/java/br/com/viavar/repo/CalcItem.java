package br.com.viavar.repo;

public class CalcItem {
	
	private Double parcelasValue;
	private Double precoEntrada;
	
	
	public Double parcela(Double preco, Integer numParcelas, Double entradaValue) {
	
		precoEntrada = preco-entradaValue;		
		parcelasValue = precoEntrada/numParcelas;		
		
		return parcelasValue;
	}

}
