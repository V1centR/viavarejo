package br.com.viavar.viavar;

import java.text.DecimalFormat;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

public class RandomRequestTests {

	@Test
	public void makeStr() throws Exception {

		int requestTimes = 10;
		int qtdParcelas = 0;
		Double valorProd = 0.0;
		String produtoNome;
		int codigoProduto;
		Double valorEntrada;
		String jsonRequestStr;

		DecimalFormat decimalF = new DecimalFormat("0.00");

		for (int i = 0; i < requestTimes; i++) {

			valorProd = makePrice();
			valorEntrada = entradaProporcional(valorProd);
			codigoProduto = gerNumber(9999);
			produtoNome = randProduto();
			qtdParcelas = gerNumber(24);

			jsonRequestStr = "{\"produto\": {\"codigo\": \"" + codigoProduto + "\", \"nome\": \"" + produtoNome
					+ "\", \"valor\": \"" + decimalF.format(valorProd)
					+ "\" }, \"condicaoPagamento\": {\"valorEntrada\": \"" + decimalF.format(valorEntrada)
					+ "\", \"qtdeParcelas\": \"" + qtdParcelas + "\" } }";

			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/api/produto/calc");

			StringEntity entityStr = new StringEntity(jsonRequestStr, "utf-8");

			httpPost.setEntity(entityStr);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			HttpResponse response = client.execute(httpPost);

			ResponseHandler<String> handler = new BasicResponseHandler();
			String responseBody = handler.handleResponse(response);

			System.out.println("\n\nREQUEST###################");
			System.out.println(jsonRequestStr + "\n");
			System.out.println("RESPONSE##################");
			System.out.println(responseBody + "\n\n");
		}
	}

	private Double makePrice() {
		double x = (Math.random() * ((2999.99 - 999.99) + 1));
		return x;
	}

	private Double entradaProporcional(Double valorProduto) {

		double percentualEntradaMax = valorProduto / 2;
		double percentualEntradaMin = valorProduto / 5;

		double x = (Math.random() * ((percentualEntradaMax - percentualEntradaMin) + 1));
		return x;
	}

	private Integer gerNumber(int limitNumber) {
		Random randNum = new Random();
		Integer num = randNum.nextInt(limitNumber) + 1;
		return num;
	}

	private String randProduto() {

		String[] items = new String[11];

		items[0] = "SmartPhone";
		items[1] = "Smart TV 50 4k";
		items[2] = "Freezer 350l";
		items[3] = "Fogão 6 bocas";
		items[4] = "Microondas 50l";
		items[5] = "Notebook i7 15pol";

		int random = new Random().nextInt(items.length);

		return items[random];
	}

}
