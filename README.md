# Worstest Movie

Este repositório contém a classificação de piores filmes para Golden Raspberry Awards

## Começo rápido

Siga as instruções abaixo para executar o projeto localmente.


### Requisitos

Você precisará do JDK 8 instalado para compilar o projeto.
Você precisará do Apache maven Versão 3.5.4 instalado para compilar o projeto

### Sobre este projeto

Esta é uma API Spring Boot Maven RestFul que usa no banco de dados H2 da memória para armazenar os piores filmes do Golden Raspberry Awards

#### Alterando a fonte de dados inicial

Para alterar a fonte de dados inicial, substitua o conteúdo do arquivo `src/main/resources/movielist.csv`

### Executando o projeto

``` ssh
 mvn spring-boot:run
```

### Acesse H2 no banco de dados de memória

- http://localhost:8080/h2-console

```
jdbc url = jdbc:h2:mem:testdb
nome de usuário = sa
senha = 
```

### API Local Restful

#### Todos os filmes
- http://localhost:8080/api/movies

#### Um único filme
- http://localhost:8080/api/movies/1

#### Um filme inexistente
- http://localhost:8080/api/movies/1000
```
{"message": "Não foi possível localizar o filme com o código: 1000"}
```

#### Todos os produtores
- http://localhost:8080/api/producers

#### Todos os produtores dos vencedores
- http://localhost:8080/api/producers/winners

#### Todos os produtores dos vencedores e respectivos intervalos entre os prêmios
- http://localhost:8080/api/producers/winners/intervals

#### Relação os produtores vencedores com maior e menor intevalo entre os prêmios
- http://localhost:8080/api/producers/winners/intervals/top-tail-awards

```
{
    "min": [
        {
            "producer": "Producer 1",
            "interval": 1,
            "previousWin": 2008,
            "followingWin": 2009
        },
        {
            "producer": "Producer 2",
            "interval": 1,
            "previousWin": 2018,
            "followingWin": 2019
        }
    ],
    "max": [
        {
            "producer": "Producer 1",
            "interval": 99,
            "previousWin": 1900,
            "followingWin": 1999
        },
        {
            "producer": "Producer 2",
            "interval": 99,
            "previousWin": 2000,
            "followingWin": 2099
        }
    ]
}
```

### Executando os testes de integração

```
 mvn verify
```
