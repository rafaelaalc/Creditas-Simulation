# Simulador de Crédito

Para rodar o projeto, faça um clone do repositório e rode a requisição no POSTMAN ou similiar de acordo com a documentação descrita abaixo. 
Ou então, caso tenha Docker instalado, rode os comandos na pasta no projeto:

Realizar o build da aplicação
```curl
docker-compose up --build -d
```

Iniciar a aplicação na porta http://localhost:8080
```
docker-compose up -d
```

# Simulador de Crédito - Documentação

A API calcula o resultado do crédito simulado ao usuário com base no valor solicitado, idade e prazo de pagamento em meses. Com estes dados é possível selecionar em qual taxa de juros anual o usuário se encaixa.

### Taxa de juros anual

A taxa de juros é calculada com base na faixa etária do cliente:

- Menor de 18 anos: Não é possível realizar uma simulação
- Até 25 anos: 5% ao ano
- De 26 a 40 anos: 3% ao ano
- De 41 a 60 anos: 2% ao ano
- Acima de 60 anos: 4% ao ano

## Schema

### Request

| nome | valor | descrição |
| ---- | ----- | --------- |
| path | `/simulate` | caminho do endpoint |
| method | `GET` | - |
| param | `value` | Valor total do empréstimo requisitado no formato `Double` |
| param | `dateOfBirth` | Data de nascimento do cliente no formato `dd/MM/yyyy` |
| param | `term` | Prazo de pagamento desejado em meses, no formato `Int` |

### Response

| nome | valor | descrição |
| ---- | ----- | --------- |
| statusCode | 200 | - |
| body | `SimulationView` | Retorna um objeto do tipo [SimulationView](#sv) |

<a name="sv"></a> **SimulationView**

| nome | valor | descrição                           |
| ---- | ----- |-------------------------------------|
| total | `String` | Valor total a ser pago em reais     |
| installmentsValue | `String` | Valor de cada parcela em reais      |
| totalInterest | `String` | Valor total de juros pagos em reais |

### Erros

| status | exception | message | descrição
| ---- | ----- | --------- | ---- |
| 400 | `BadRequestException` | `O serviço solicitado só pode ser contratado para maiores de 18 anos.` | Ocorre quando o usuário insere uma data de nascimento inferior a 18 anos. |
| 400 | `BadRequestException` | `Valor solicitado inválido. Por favor, Tente novamente.` | Ocorre quando o usuário insere um valor negativo ou zero |
| 400 | `BadRequestException` | `Prazo de pagamento inválido. Tente novamente.` | Ocorre quando o usuário insere um prazo de pagamento negativo ou zero |

## Exemplos

<details>
    <summary><b>Simulação 200 OK</b></summary>

### Request
```shell
curl --location 'http://localhost:8080/simulate?value=30000.0&dateOfBirth=09%2F01%2F1995&term=36' \
--header 'Content-Type: application/json'
```

### Response

```json
{
    "total": "R$ 31.407,71",
    "installmentsValue": "R$ 872,44",
    "totalInterest": "R$ 1.407,71"
}
``` 

</details>

<details>
    <summary><b>Simulação 400 BAD REQUEST</b></summary>

### Request
```shell
curl --location 'http://localhost:8080/simulate?value=-5000&dateOfBirth=09%2F01%2F1995&term=36' \
--header 'Content-Type: application/json'
```

### Response

```json
{
    "timestamp": "2025-08-03T17:41:56.8181018",
    "status": 400,
    "name": "BAD_REQUEST",
    "message": "Valor solicitado inválido. Por favor, Tente novamente.",
    "path": "/simulate"
}
``` 

</details>


