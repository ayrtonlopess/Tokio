# Custumer Service

### Requisitos

1. JDK 8
1. Maven 3

### Rodando

1. Clone o projeto: `https://github.com/leonardohenrique/tokio-test.git`
1. Entre na pasta `tokio-test` e execute: `mvn spring-boot:run`
1. Acesse: `http://localhost:8080/customers`

	----- Orientado a Objetos -----

1 - O que é injeção de dependencia ? 
Nesta solução as dependências entre os módulos não são definidas programaticamente,
mas sim pela configuração de uma infraestrutura de software container
que é responsável por injetar em cada componente suas dependências declaradas.
 
2 - O que voce entende por SOLID ? 

SOLID é um acrônimo criado por Michael Feathers que representa os 5 princípios da programação orientada a objetos 
SRP - OCP - LSP - ISP - DIP

3 - Voce conhece o design pattern Strategy ? 
O padrão Strategy permite definir novas operações sem alterar as classes dos elementos sobre os quais opera. 
Segundo o catálogo GOF o padrão tem como meta: Definir uma família de algoritmos, encapsular cada uma delas e torná-las intercambiáveis. 
Strategy permite que o algoritmo varie independentemente dos clientes que o utilizam.


 ----- Spring boot  -----
 
 1 - Quais subprojetos do spring voce conhece ?
 
 Spring boot, Spring data e Spring MVC 
 
 
 2 - como podemos criar e injetar beans no spring ?
 
 usando anotações como por exemplo: @Bean @Component @Configuration
 
 3 - Como usar transações no Spring ?
 
 Habilitando transações via anotações no applicationContext.xml
 Após isso você estará apto a usar a anotação @Transactional afim de definir que determinado método deverá esta dentro de uma transação.
 
 ----- REST  -----
 
 
 1 - O que voce entende sobre o que sao servicos RestFul ?
 
 capacidade de determinado sistema aplicar os princípios de REST.
 
 2 - Cite algumas boa pratica envolvendo rest ?
 
 Utilizar os metodos HTTP 
 Evitar alterações na Uris
 Utilizar o mesmo padrão de URI na identificação dos recursos
 
 3 - Cite os metodos HTTP mais utilizados  ?
 
 GET: Obter os dados de um recurso.
 POST: Criar um novo recurso.
 PUT: Substituir os dados de um determinado recurso.
 DELETE: Excluir um determinado recurso.
 
 4 - Qual a diferenca entre os codigos de resposta HTTP 200, 400 e 500 ? 
 
 200 - Em requisições GET, PUT e DELETE executadas com sucesso.
 
 400 - Em requisições cujas informações enviadas pelo cliente sejam invalidas. 
 
 500 - Em requisições onde um erro tenha ocorrido no servidor.
 
 

