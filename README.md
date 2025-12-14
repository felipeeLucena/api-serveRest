# API ServeRest – Automated API Tests

Projeto de **automação de testes de API** utilizando **Java, RestAssured e JUnit 5**, aplicado sobre a API pública **ServeRest**.
Este repositório faz parte do meu **portfólio de QA** e demonstra organização, boas práticas e uso de CI.

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

* Camada **Service** responsável pelas requisições
* Testes não utilizam `given()` diretamente
* Autenticação centralizada
* Token Bearer reutilizado via `AuthContext`
* Separação clara entre cenários de **sucesso** e **falha**

---

## 🔐 Autenticação

* Login executado automaticamente antes dos testes autenticados
* Token salvo em memória
* Utilizado em endpoints protegidos

---

## ✅ Cenários Implementados

### Login

* Login com sucesso
* Login com credenciais inválidas
* Campos obrigatórios ausentes

### Usuários

* Listar usuários autenticado
* Acesso negado sem token
* Criar usuário com sucesso (POST)
* Validação de erro ao criar usuário com payload inválido

---

## ▶️ Executar os Testes

```bash
mvn clean test
```

---

## 🤖 CI – GitHub Actions

* Executa automaticamente em:

  * Push na branch `main`
  * Pull Requests
* Execução manual habilitada (`workflow_dispatch`)

Arquivo:

```
.github/workflows/ci.yml
```

---

## 🧠 Boas Práticas

* Código organizado por responsabilidade
* Reuso de código
* Manutenção facilitada
* Estrutura escalável para novas APIs
* Padrão aplicado em ambientes reais de QA

---

## 📌 Próximos Passos

* Testes de PUT / DELETE
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
