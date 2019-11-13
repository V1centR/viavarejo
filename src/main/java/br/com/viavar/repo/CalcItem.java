package br.com.viavar.repo;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.viavar.model.SelicData;

public class CalcItem {
	
	private Double parcelasValue = 0.0;
	private Double precoEntrada = 0.0;
	private Double parcelaItem = 0.0;
	public List<String> parcelas;
	public Double selicAccm = 0.0;
	private Double valueBkp = 0.0;
	
	//Apply dynamic installments
	private Double jurosMes = 1.15;
	private ObjectNode objNode;
	
	
	public ArrayNode parcela(Double preco, Integer numParcelas, Double entradaValue) throws IOException {
	
		precoEntrada = preco-entradaValue;		
		parcelasValue = precoEntrada/numParcelas;		
		parcelaItem = precoEntrada/parcelasValue;	
		
		return setProdutoObject(numParcelas);
	}
	
	/*
	 * Get time to calc. 
	 */
	protected String setDays() {
		
		
		
		
		return null;
	}
	
	
	/*
	 * Method generate json object
	 */
	protected ArrayNode setProdutoObject(int numParcelas) throws IOException {
		
		DecimalFormat dformat = new DecimalFormat("####0.00");
		
		ObjectMapper jsonObj = new ObjectMapper();
		ArrayNode arrayNode = jsonObj.createArrayNode();
		
		SelicData[] selictObj = jsonObj.readValue(getBcbSelic(),SelicData[].class);
		
		//Sum Selic last 30 days
		for(SelicData valor: selictObj) {
			
			if(Math.abs(valor.getValor() - valueBkp) >= 0.000001) {
				System.out.println("VALUE to SUM:: " + valor.getValor());
				selicAccm += valor.getValor();
				valueBkp = valor.getValor();
			}
		}
		
		System.out.println("Selic Acumulada::: " + selicAccm);
		
		if(numParcelas <7) {
			jurosMes = 0.0;
		}
	
		
		for(int i = 1; i<=parcelaItem;i++) {
			
			objNode = jsonObj.createObjectNode();
			
			objNode.put("numeroParcela", i);
			objNode.put("valor", dformat.format(applyInstallments(parcelasValue,jurosMes)));
			objNode.put("taxaJurosAoMes", jurosMes);
			//set item into array node
			arrayNode.add(objNode);
			
			
			if(i == parcelaItem) {
				ObjectNode selicNode = jsonObj.createObjectNode();
				selicNode.put("description", "Taxa Selic acumulada base 30 dias");
				selicNode.put("selicAcumulada", selicAccm);
				arrayNode.add(selicNode);
			}
			
			
		}
		
		return arrayNode;
	}
	
	private Double applyInstallments(Double preco, Double percent) {		
		parcelasValue = preco + (preco*percent)/100;
		return parcelasValue;
	}
	
	public String getBcbSelic() {
		
		LocalDateTime localDate = LocalDateTime.now();
		String localDateString = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		DateTime dataFinal = new DateTime(localDateString);
		DateTime retroDate = dataFinal.minusDays(30);
		
		Date startDateJava = new Date();
		Format f = new SimpleDateFormat("dd/MM/yyyy");
		
	    String startDate = f.format(startDateJava);
	    String endDate = f.format(retroDate.toDate());
		
		final String bcbService = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.11/dados?formato=json&dataInicial="+ endDate +"&dataFinal=" + startDate +"";
		
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(bcbService, String.class);
		
		return result;
	}

}
