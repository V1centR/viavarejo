package br.com.viavar.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.viavar.model.Pagamento;
import br.com.viavar.model.Produto;
import br.com.viavar.repo.CalcItem;

@RestController
@RequestMapping(value="/api/produto")
public class ProductController {
	
	@CrossOrigin
	@PostMapping(path="/calc",consumes = "application/json", produces = "application/json")
	public @ResponseBody String calcProduto(@RequestBody String dataItem) throws JsonParseException, JsonMappingException, IOException{


		//Extract Json nodes
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonItem = objectMapper.readTree(dataItem);
		
		Produto produto 			= objectMapper.readValue(jsonItem.get("produto").toString(), Produto.class);
		Pagamento condicaoPagamento = objectMapper.readValue(jsonItem.get("condicaoPagamento").toString(), Pagamento.class);


		System.out.println("Request ok#### " + produto.nome);
			
		CalcItem calcular = new CalcItem();
		calcular.parcela(produto.getValor(), condicaoPagamento.getQtdeParcelas(), condicaoPagamento.getValorEntrada());
		
		return "test ok";
	}
	
	
}
