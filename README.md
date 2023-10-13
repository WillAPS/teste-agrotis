# TESTE TÉCNICO - AGROTIS

**Descrição**: O teste consiste em construir uma API para ser utilizada na construção de uma
tela;
---
## Pré-requisitos

    - Java JDK 11
    - Gradle
    - MySQL
---

## Inicio

    git clone https://github.com/WillAPS/teste-agrotis.git

## Configuração MySQL

    url: jdbc:mysql://localhost:3306/db
    username: root
    password: 1234

    Ao rodar o sistema pela primeira vez são geradas as tabelas
    pessoa, propriedade e laboratório as duas ultimas são populadas
    com 10 registros cada para auxiliar nos testes
---

## Requisições HTTP

    Requisições Disponíveis - URL BASE: http://localhost:8080
---
## 1- POST: "/pessoa/cadastrar"

|      Campo       |  Tipo  | Obrigatório |
|:----------------:|:------:|:-----------:|
|       nome       | String |      X      |
|   dataInicial    |  Date  |      X      |
|    dataFinal     |  Date  |      X      |
| infosPropriedade |   *    |      X      |
|   laboratorio    |   *    |      X      |
|   observacoes    | String |             |
    
- infosPropriedade e laboratorio necessitam desses campos 

| Campo |  Tipo  | Obrigatório |
|:-----:|:------:|:-----------:|
|  id   |  Long  |      X      |
| nome  | String |      X      |
---
Exemplo requisição válida 
---
```
{
    "nome": "Liam",
    "dataInicial": "2024-10-15T09:15:00Z",
    "dataFinal": "2024-10-15T17:30:00Z",
    "infosPropriedade": {
        "id": 5,
        "nome": "Fazenda Beta"
    },
    "laboratorio": {
        "id": 8,
        "nome": "TechLab Solutions"
    },
    "observacoes": "Observacoes adicionais"
}
```

Exemplo requisição inválida
--- 
```
Exemplo: Sem os valores de nome e sem o id da propriedade
{
    "nome": "",
    "dataInicial": "2024-10-15T09:15:00Z",
    "dataFinal": "2024-10-15T17:30:00Z",
    "infosPropriedade": {
        "nome": "Fazenda Beta"
    },
    "laboratorio": {
        "id": 8,
        "nome": "TechLab Solutions"
    },
    "observacoes": "Observacoes adicionais"
}
```

Possivéis retornos para essa rota
---
    Caso a requisição seja válida: 
        Status 200 - Pessoa cadastrada com sucesso

    Caso os valores de propriedade ou laboratório sejam inválidos
        Status 404 - Propriedade não encontrada
        Status 404 - Laboratório não encontrada
    
    Caso algum campo não siga os padrões definidos é retornado um json com os erros
    
    Exemplos: 
    {
        "message": "Requisição Invalida",
        "errors": {
            "infosPropriedade.id": [
                "id não pode estar branco ou nulo"
            ]
    }

    {
    "message": "Requisição Invalida",
    "errors": {
        "nome": [
            "nome não pode estar branco ou nulo"
        ],
        "infosPropriedade.id": [
            "id não pode estar branco ou nulo"
        ]
    }
---

## 2- PUT: "/pessoa/atualizar"

| Campo |  Tipo  | Obrigatório |
|:-----:|:------:|:-----------:|
|  id   |  Long  |      X      |
| nome  | String |      X      |
---
Exemplo requisição válida
---
```
{
    "id" : 1,
    "novo_nome": "Liam"
}
```
---
Possivéis retornos para essa rota
---
    Caso a requisição seja válida: 
        Status 200 - Pessoa atualizada com sucesso

    Caso a pessoa não exista:
        Status 404 - Pessoa não encontrada

    Caso algum campo não siga os padrões definidos é retornado um json com os erros
    
    Exemplo: 
    {
        "message": "Requisição Invalida",
        "errors": {
            "nome": [
                "novo_nome não pode estar branco ou nulo"
            ]
        }
    }
---
## 3- GET: "/laboratorio/listar"


|        Campo        |  Tipo   |                                     Função                                     |
|:-------------------:|:-------:|:------------------------------------------------------------------------------:|
|  quantidade_minima  | Integer |     Lista laboratórios que possuem no minimo X pessoas (Campo obrigatório)     |
|       palavra       | String  |  Lista laboratórios que possuem pessoas que tem essa palavra nas observações   |
| data_inicial_comeco |  Date   | Lista laboratórios que possuem pessoas que tem data inicial entre começo e fim |
|  data_inicial_fim   |  Date   |                                       X                                        |
|  data_final_comeco  |  Date   |  Lista laboratórios que possuem pessoas que tem data final entre começo e fim  |
|   data_final_fim    |  Date   |                                       X                                        |
| ordenar_quantidade  | Boolean |      Ordena os laboratórios por quantidade de pessoa em ordem decrescente      |

---
Exemplos de querystring 
---
    - Lista laboratórios com no mínimo uma pessoa cadastrada
    http://localhost:8080/laboratorio/listar?quantidade_minima=1

    - E que tem pessoas com a palavra teste nas observações
    http://localhost:8080/laboratorio/listar?quantidade_minima=1&palavra=teste
    
    - E ordenar pela quantidade de pessoas cadastradas nesses laboratórios
    http://localhost:8080/laboratorio/listar?quantidade_minima=1&palavra=teste&ordenar_quantidade=true
