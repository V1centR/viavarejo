package br.com.viavar.repo;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CalcItem {
	
	private Double parcelasValue = 0.0;
	private Double precoEntrada = 0.0;
	private Double parcelaItem = 0.0;
	public List<String> parcelas;
	
	//Apply dynamic installments
	private Double jurosMes = 1.15;
	
	
	public ArrayNode parcela(Double preco, Integer numParcelas, Double entradaValue) throws IOException {
	
		precoEntrada = preco-entradaValue;		
		parcelasValue = precoEntrada/numParcelas;		
		parcelaItem = precoEntrada/parcelasValue;
		
		
		if(numParcelas > 6) {
			System.out.println("Parcelas:  " + setProdutoObject().toString());
		}else {
			System.out.println("Parcelamento sem juros!");
		}
		
		System.out.println("preco - entrada = " + precoEntrada);
		System.out.println("Valor das parcelas:: " + parcelasValue);
		System.out.println("Quantidade da parcelas:: " + Math.round(parcelaItem));
		
		
		return setProdutoObject();
	}
	
	/*
	 * Method generate json object
	 */
	protected ArrayNode setProdutoObject() throws IOException {
		
		DecimalFormat dformat = new DecimalFormat("####0.00");
		
		ObjectMapper jsonObj = new ObjectMapper();
		ArrayNode arrayNode = jsonObj.createArrayNode();
		
		for(int i = 1; i<parcelaItem;i++) {
			
			ObjectNode objNode = jsonObj.createObjectNode();
			
			objNode.put("numeroParcela", i);
			objNode.put("valor", dformat.format(applyInstallments(parcelasValue,jurosMes)));
			objNode.put("taxaJurosAoMes", jurosMes);
			arrayNode.add(objNode);
		}
		
		return arrayNode;
	}
	
	private Double applyInstallments(Double preco, Double percent) {		
		parcelasValue = preco + (preco*percent)/100;
		return parcelasValue;
	}
	
	public String getBcbSelic() {
		
		final String bcbService = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.11/dados?formato=json";
		
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(bcbService, String.class);
	    
	   
	     
	    
	    
	    System.out.println(result);
		
		
		return null;
	}

}
