# API ServeRest – Automated API Tests

Projeto de **automação de testes de API** utilizando **Java, RestAssured e JUnit 5**, aplicado sobre a API pública **ServeRest**.
Este repositório faz parte do meu **portfólio de QA** e demonstra organização, boas práticas e uso de CI/CD.

---

## 🚀 Tecnologias

* Java 17
* RestAssured
* JUnit 5
* Hamcrest
* Maven
* GitHub Actions (CI)

---

## 📁 Estrutura do Projeto

```
src/test/java
├── config
│   └── BaseConfig.java
│
├── core
│   ├── BaseTest.java
│   ├── BaseService.java
│   ├── AuthContext.java
│   └── AuthenticatedBaseTest.java
│
├── model
│   ├── LoginRequest.java
│   └── UserRequest.java
│
├── service
│   ├── LoginService.java
│   └── UserService.java
│
├── utils
│   ├── api
│   ├── factory
│   ├── messages
│   └── status
│
├── LoginTest.java
└── UserTest.java
```

---

## 🧪 Estratégia de Testes

* Camada **Service** responsável pelas requisições HTTP
* Testes não utilizam `given()` diretamente
* Autenticação centralizada
* Token Bearer reutilizado via `AuthContext`
* Separação clara entre cenários de **sucesso** e **falha**
* Organização por endpoint e verbo HTTP

---

## 🔐 Autenticação

* Login executado automaticamente antes dos testes autenticados
* Token Bearer salvo em memória
* Utilizado em endpoints protegidos

---

## ✅ Cenários Implementados

### 🔑 Login

* Login com sucesso
* Login com credenciais inválidas
* Validação de campos obrigatórios

### 👤 Usuários

#### GET /usuarios

* Listar usuários autenticado
* Acesso negado sem token

#### POST /usuarios

* Criar usuário com sucesso
* Erro ao criar usuário com payload inválido

#### PUT /usuarios/{id}

* Atualizar usuário com sucesso
* Erro ao atualizar usuário com payload inválido

#### DELETE /usuarios/{id}

* Deletar usuário com sucesso
* Comportamento validado ao deletar usuário inexistente (conforme contrato da API)

---

## ▶️ Executar os Testes

```bash
mvn clean test
```

---

## 🤖 CI – GitHub Actions

Pipeline configurado para:

* Push na branch `main`
* Pull Requests
* Execução manual via botão **Run workflow**

Arquivo:

```
.github/workflows/ci.yml
```

---

## 🧠 Boas Práticas

* Código organizado por responsabilidade
* Reuso de código
* Estrutura escalável
* Padrões utilizados em projetos reais de QA

---

## 📌 Próximos Passos

* Validação de schemas
* Relatórios (Allure)
* Paralelismo

---

## 👤 Autor

Felipe
QA Engineer – Automação de APIs

---

## 🔗 API Testada

[https://serverest.dev](https://serverest.dev)
