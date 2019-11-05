package br.com.viavar.model;

public class Produto {
	
	/*
	 * { "produto": 
	 * { "codigo": 123, 
	 * "nome": "Nome do Produto", 
	 * "valor": 9999.99 },
	 *  
	 * "condicaoPagamento": 
	 * { "valorEntrada": 9999.99, 
	 * "qtdeParcelas": 999 
	 * } }
	 * 
	 * */
	
	public String nome;
	public Double valor;
	public Integer codigo;	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	

}
