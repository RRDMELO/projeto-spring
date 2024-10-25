# API REST Cliente

## Sumário
- [Visão Geral](#visão-geral)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Endpoints](#endpoints)
- [Modelos de Dados](#modelos-de-dados)
- [Documentação](#documentação)

## Visão Geral
Esta é uma API RESTful construída com Java e Spring Boot, utilizando Swagger para documentação. A API gerencia informações de clientes, permitindo operações de CRUD.

## Instalação

### Pré-requisitos
- Java 11 ou superior
- Maven 3.6.3 ou superior

### Clonando o Repositório
```bash
git clone https://github.com/seu-usuario/sua-repositorio.git
cd sua-repositorio
```

### Construindo o Projeto
```bash
mvn clean install
```

### Executando a Aplicação
```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

## Configuração
Adicione as seguintes dependências ao arquivo `pom.xml` para habilitar o Swagger:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```

## Endpoints

### Listar Clientes
```
GET /clientes
```
Retorna a lista de todos os clientes.

### Encontrar Cliente pelo Id
```
GET /clientes/{id}
```
Retorna um cliente específico pelo ID.

### Criar Cliente
```
POST /clientes
```
Cria um novo cliente.

### Atualizar Cliente
```
PUT /usuarios/{id}
```
Atualiza um cliente existente.

### Deletar Cliente
```
DELETE /usuarios/{id}
```
Deleta um cliente existente.

## Modelos de Dados

### ClienteModel
```
@Getter
@Setter
@Entity
@Table(name = "cliente")
public class ClienteModel extends RepresentationModel<ClienteModel> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
}
```

### ClienteRequestBody
```
public record ClienteRequestBody(
        @Size(min = 11, max = 11) @NotBlank String cpf,
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotNull @Past LocalDate dataNascimento) {}
```

## Documentação
A documentação completa da API gerada pelo Swagger está disponível em:
```
http://localhost:8080/swagger-ui/index.html
```

## Contribuições
Pull requests são bem-vindos. Para mudanças importantes, abra primeiro uma issue para discutir o que você gostaria de mudar.

Por favor, certifique-se de atualizar os testes conforme apropriado.

## Licença
[MIT](LICENSE)
