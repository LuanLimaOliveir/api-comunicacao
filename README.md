# API Comunicação
Plataforma de comunicação para o agendamento de envio de mensagens

## Tecnologias e ferramentas

- [Maven](https://maven.apache.org/)
- [Flyway](https://flywaydb.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)
- [Java11](https://openjdk.java.net/)
- [Lombok](https://projectlombok.org/)
- [Bean Validation](https://hibernate.org/validator/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data](https://spring.io/projects/spring-data)
- [Swagger](https://swagger.io/)


## Com inicializar a API localmente (a partir da IDE)?

_1º Passo_
	Criar a base de dados `agendamento_comunicacao`, para isso execute o script abaixo:
```sql
CREATE DATABASE agendamento_comunicacao;
```

_2º Passo_
	Criar o usuário `luizalabs` e aplicar permissões (DDL e DML) para utilização da base de dados `agendamento_comunicacao`, para isso execute o script abaixo:
```sql
CREATE USER luizalabs WITH ENCRYPTED PASSWORD 'luizalabs';
GRANT ALL PRIVILEGES ON DATABASE agendamento_comunicacao TO luizalabs;
```

_3º Passo_
    Criar a estrutura (TABLE, VIEW, SEQUENCE e TRIGGER) da base de dados `agendamento_comunicacao` a partir das migrations, para isso execute o comando abaixo utilizando o Maven:
```ssh
mvn flyway:migrate
```
_Info: Segue abaixo o diretório onde se encontram as migrations no projeto_  
```path
/src/main/resources/db/migrations
```

**Após essas etapas, você pode realizar a inicialização da API a partir da sua IDE**

## Como acessar a documentação da API (via Swagger)?
Para acessar a documentação acesse o link a seguir, [http://localhost:8080/swagger-ui.html#]

## Com inicializar a API a partir do Docker?
Para cria a imagem do container da API e inicializar os serviços, execute o comando abaixo:

```docker
docker-compose up -d --build 
```

Para parar o container e os serviços associados, execute o comando abaixo:

```docker
docker-compose down -v
```

## Licença

MIT

   [http://localhost:8080/swagger-ui.html#]: <http://localhost:8080/swagger-ui.html#>
