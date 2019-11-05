package br.com.viavar.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/produto")
public class ProductController {
	
	@CrossOrigin
	@RequestMapping("/calc")
	public @ResponseBody String findAll(){
		return "Test OK";
	}
}
