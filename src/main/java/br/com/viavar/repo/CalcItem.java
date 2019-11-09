package br.com.viavar.repo;

import java.text.DecimalFormat;
import java.util.List;

public class CalcItem {
	
	private Double parcelasValue = 0.0;
	private Double precoEntrada = 0.0;
	private Double parcelaItem = 0.0;
	public List<String> parcelas;
	private Double precoFinal = 0.0;
	
	
	public Double parcela(Double preco, Integer numParcelas, Double entradaValue) {
	
		precoEntrada = preco-entradaValue;
		
		parcelasValue = precoEntrada/numParcelas;
		
		parcelaItem = precoEntrada/parcelasValue;
		
		System.out.println("Parcela " + parcelaItem);
		
		DecimalFormat dformat = new DecimalFormat("####0.00");
		
		for(int i = 1; i<parcelaItem;i++) {
			
			System.out.println("Parcela " + i + " R$ " +  dformat.format(Double.parseDouble(parcelasValue.toString())));
			
		}
		
		System.out.println("preco - entrada = " + precoEntrada);
		System.out.println("Valor das parcelas:: " + parcelasValue);
		System.out.println("Quantidade da parcelas:: " + Math.round(parcelaItem));
		
		//Apply Juros
		System.out.println("Parcelas com juros:: " + dformat.format(applyInstallments(parcelasValue,2.15)));
		
		return parcelasValue;
	}
	
	private Double applyInstallments(Double preco, Double percent) {		
		precoFinal = preco + (preco*percent)/100;
		return precoFinal;
	}

}
