# Desafio Backend Criptografia
Este projeto foi feito para estudo e aperfeiçoamento das ferramentas Java, Spring e Criptografia de dados sensíveis. A ideia partiu como uma proposta de solução ao Desafio de Criptografia do repositório do Backend Brasil.

## Sobre o desafio
Dado uma entidade que contém campos sensíveis deve ser implementado algum tipo de criptografia para proteção de dados. Também, a criptografia deve ser transparente para as camadas de serviço da aplicação, ou seja, para as requisições da nossa API os campos devem ser entregues descriptografados e persistidos criptografados no banco de dados. O desafio com maior detalhes você pode encontrar no repositório do [Backend Brasil](https://github.com/backend-br/desafios).

## Sobre o projeto
Neste projeto foi criada uma aplicação CRUD simples em Java/Spring seguindo o padrão MVC (Model View Controller) com o desafio de criptografia de dados sensíveis, foi utilizado o H2 como banco de dados em memória e para criptografia é utilizado na aplicação o algoritmo AES existente na biblioteca do spring.security.

### Teste das API's, rotas, criptografia e persistência de dados no banco de dados:

- Criação da entidade em "/transaction":

![post-create-insomnia](https://res.cloudinary.com/dtu1wwbk6/image/upload/v1698273515/readme%20aleatorio/ut11lsswxenmvnh17wch.png)

- Entidade criada persistida no banco de dados com dados criptografados:

![h2-select](https://res.cloudinary.com/dtu1wwbk6/image/upload/v1698273515/readme%20aleatorio/lfotdyaxa4qfb9yzz2dk.png)

- Requisição da consulta da entidade no banco de dados, entidade retornada com dados descriptografados:

![get-search-insomnia](https://res.cloudinary.com/dtu1wwbk6/image/upload/v1698273515/readme%20aleatorio/meglfv8w65os4135dzsw.png)
