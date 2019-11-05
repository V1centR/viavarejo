package br.com.viavar.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.viavar.model.Produto;

@RestController
@RequestMapping(value="/api/produto")
public class ProductController {
	
	@CrossOrigin
	@PostMapping(path="/calc",consumes = "application/json", produces = "application/json")
	public @ResponseBody String calcProduto(@RequestBody String dataItem){
		
		JSONObject jsonItem = new JSONObject(dataItem);
		
		JSONObject produto = (JSONObject) jsonItem.get("produto");
		JSONObject pagamento = (JSONObject) jsonItem.get("condicaoPagamento");
		
		
		return "Test OK";
	}
}
