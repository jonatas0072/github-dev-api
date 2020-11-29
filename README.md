# API Github Developers! 

Este Projeto se encontra hospedado na Heroku e pode ser acessado nesta URL:
##### URL-BASE : https://github-dev-api.herokuapp.com/

##### OBS : Utilize o login abaixo para pode criar o token e assim tiver acesso as API's:
email : `jonatas0072@gmail.com`
senha : `1234567890`
role : `ADMIN` 

[![Author](http://img.shields.io/badge/Arthur%20-Jonatas%20Macedo-green)](https://github.com/jonatas0072/github-api/blob/master/LICENSE)

#  Projeto
> O projeto consite em consumir as API's do github e gerar um ranking dos mesmos.

### Tecnologias

Este projeto foi desenvolvido utilizando as seguintes tecnologia:

* [Java](https://www.java.com/en/download/help/java8.html) - Linguagem de Programação do Projeto
* [Spring Boot 2.2.10 RELEASE](https://spring.io/projects/spring-boot) - Projeto do framework Spring utilizado para agilizar a configuração e publicação da aplicação
* [Hibernate](https://hibernate.org/) -Ferramenta ORM
* [JPA](https://www.oracle.com/java/technologies/persistence-jsp.html) - API para frameworks de persistência de dados
* [Postgres](https://www.postgresql.org/) - Banco de dados Relacional
* [Lombok](https://projectlombok.org/) - Utilizada para deixa o codigo menos verboso e aumento de agilidade
* [Junit 5](https://junit.org/junit5/) - Framework utilizado para testes
* [Mockito](https://site.mockito.org/) - Estrutura de teste de código aberto para Java liberada sob a licença MIT. A estrutura permite a criação de objetos duplos de teste em testes de unidade automatizados com o objetivo de desenvolvimento orientado a teste ou desenvolvimento orientado a comportamento.
* [H2](https://www.h2database.com/html/main.html) - Bando de dados relacional em memória
* [JWT](https://jwt.io/) - Token para assinatura de dados JSON
* [SpringSecurty](https://spring.io/projects/spring-security) - Ferramenta para autenticação e autorização de usuarios
* [Unirest](http://kong.github.io/unirest-java/) - Ferramenta para chamadas de API's externas
* [Swagger](https://swagger.io/) - Ferramenta para mapear e documentar as API's
* [Jackson](https://github.com/FasterXML/jackson) - Biblioteca para serializar e desserializar objetos Java para JSON.

## Requisitos para Compilação

## Step 1

Faça o Download do projeto:
````
git clone https://github.com/jonatas0072/github-api.git
````

## Step 2

Para o compilar e tesar o projeto é necessário instalar as seguintes tecnologias:

Java 8 - Caso você não tenha o Java 8 instalado bastar fazer o download por este link (Linux e WIndows): [Download Java 8](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)

Postgres - Caso você não tenha o Postgres instalado basta fazer o download por este link (Linux e WIndows): [Download Postgres](https://www.postgresql.org/download/)

OBS : apos a instalação do postgres sera necessario criar o database com o nome de `github`
## Instalação do Postgres via Docker (Recomendado)

Se assim como eu você prefere usar o Docker para a instalação do banco de dados segue os comandos para instalação via Docker:
Não tem o Docker instalado? Faça o Download por este link: [Download Docker](https://www.docker.com/products/docker-desktop)

Após instalar e iniciar o Docker execute o seguinte comando para obter uma imagem do Postgres:
````
docker pull postgres
````
Com esse comando criamos um contêiner com a conexão com o banco de dados Postgres na porta padrão :5432 com a senha  **postgres**
````
docker run --name some-postgres -e POSTGRES_PASSWORD=postgres -d postgres
````
## Step 3 - Optional
Este projeto foi configurado para ser virtualizado no docker.

Empacote o projeto com o seguinte comando. Para este empacotamento não tem necessidade de rodar os testes como mostrado abaixo:
`mvn clean package -DskipTests`

Para contruir uma image do projeto em docker, execute :
 
`docker build -f Dockerfile -t github .`

Após isto voce ira ter criado uma imagem do projeto, com isso é so criar um container e roda um docker run assim :

`docker run -p 8080:8080 github`

Com isso voce deve ter o aplicativo rodando em sua maquina.

### Como contribuir?
* Fork o repositório
* Crie uma branch: `git checkout -b my-feature`
* Commit suas mudanças: `git commit -m 'feat: My new feature`
* Push a sua branch: `git push origin my-feature`

### Licença
Esse projeto está sob a licença [Apache 2.0](https://github.com/jonatas0072/github-api/blob/master/LICENSE).