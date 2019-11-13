### Features

- API REST return list of financing installments with interests Selic rate.
- API with Swagger in atachment for test support.
- Developer vicentcdb@gmail.com.


### Running this project:
- Clone this project with: https://github.com/V1centR/viavarejo.git
- Run: <b>mvn clean install</b>
- Run: <b>mvn spring-boot:run</b>
- In your REST client "Postman" use this endpoint: POST http://127.0.0.1:8080/api/produto/calc

### Running this project JAVA:
- Clone this project with: https://github.com/V1centR/viavarejo.git
- Run: <b>mvn clean install</b>
- Run: java -jar target/viavar-0.0.1-SNAPSHOT.war
- In your REST client "Postman" use this endpoint: POST http://127.0.0.1:8080/api/produto/calc


### Request example:
```
// http://127.0.0.1:8080/api/produto/calc
{
   "produto": {
      "codigo": 8888,
      "nome": "SmartPhone",
      "valor": 599.99
   },
   "condicaoPagamento": {
      "valorEntrada": 199.99,
      "qtdeParcelas": 7
   }
}
```

### Response example:
```
[
    {
        "numeroParcela": 1,
        "valor": "57.80",
        "taxaJurosAoMes": 1.15
    },
    {
        "numeroParcela": 2,
        "valor": "58.46",
        "taxaJurosAoMes": 1.15
    },
    {
        "numeroParcela": 3,
        "valor": "59.14",
        "taxaJurosAoMes": 1.15
    },
    {
        "numeroParcela": 4,
        "valor": "59.82",
        "taxaJurosAoMes": 1.15
    },
    {
        "numeroParcela": 5,
        "valor": "60.51",
        "taxaJurosAoMes": 1.15
    },
    {
        "numeroParcela": 6,
        "valor": "61.20",
        "taxaJurosAoMes": 1.15
    },
    {
        "numeroParcela": 7,
        "valor": "61.90",
        "taxaJurosAoMes": 1.15
    },
    {
        "description": "Taxa Selic acumulada base 30 dias",
        "selicAcumulada": 0.039857000000000004
    }
]
```