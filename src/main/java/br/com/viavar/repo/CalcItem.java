package br.com.viavar.repo;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CalcItem {
	
	private Double parcelasValue = 0.0;
	private Double precoEntrada = 0.0;
	private Double parcelaItem = 0.0;
	public List<String> parcelas;
	private Double precoFinal = 0.0;
	
	
	public Double parcela(Double preco, Integer numParcelas, Double entradaValue) throws IOException {
	
		precoEntrada = preco-entradaValue;		
		parcelasValue = precoEntrada/numParcelas;		
		parcelaItem = precoEntrada/parcelasValue;
		
		System.out.println("Parcela " + parcelaItem);
		
		DecimalFormat dformat = new DecimalFormat("####0.00");
		
		
		if(numParcelas > 6) {

//			for(int i = 1; i<parcelaItem;i++) {
//				
//				System.out.println("Parcela " + i + " R$ " +  dformat.format(applyInstallments(parcelasValue,1.15)));
//			}
			
			//setProdutoObject();
			System.out.println("Parcelas:  " + setProdutoObject().toString());
			
			
		}else {
			System.out.println("Parcelamento sem juros!");
		}
		
		System.out.println("preco - entrada = " + precoEntrada);
		System.out.println("Valor das parcelas:: " + parcelasValue);
		System.out.println("Quantidade da parcelas:: " + Math.round(parcelaItem));
		
		
		return parcelasValue;
	}
	
	/*
	 * Method generate json object
	 */
	protected ArrayNode setProdutoObject() throws IOException {
		
		DecimalFormat dformat = new DecimalFormat("####0.00");
		
		ObjectMapper jsonObj = new ObjectMapper();
		ArrayNode arrayNode = jsonObj.createArrayNode();
		
		for(int i = 1; i<parcelaItem;i++) {
			
			//System.out.println("Parcela " + i + " R$ " +  dformat.format(applyInstallments(parcelasValue,1.15)));
			ObjectNode objNode = jsonObj.createObjectNode();
			
			objNode.put("numeroParcela", i);
			objNode.put("valor", dformat.format(applyInstallments(parcelasValue,1.15)));
			
			arrayNode.add(objNode);
			
		}
		
		return arrayNode;
	}
	
	private Double applyInstallments(Double preco, Double percent) {		
		parcelasValue = preco + (preco*percent)/100;
		return parcelasValue;
	}

}
