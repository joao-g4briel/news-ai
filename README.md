# 📰 News AI — Resumo Diário de Notícias com IA

Aplicação Spring Boot que busca as principais manchetes globais via **NewsAPI** e gera um relatório diário de notícias em **Português Brasileiro** usando o modelo de IA **Ollama Mistral 7b** rodando localmente.

---

## 🚀 Funcionalidades

- Busca manchetes em tempo real via [NewsAPI](https://newsapi.org/)
- Resume e traduz as notícias para Português Brasileiro usando Mistral 7b via Ollama
- Formata o resultado como um relatório jornalístico profissional com categoria, região, resumo e impacto por artigo
- Roda 100% localmente — sem necessidade de API de IA paga

---

## 🛠️ Tecnologias

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 21 |
| Framework | Spring Boot 3 |
| Cliente HTTP | RestTemplate |
| Modelo de IA | Mistral 7b (via Ollama) |
| Fonte de Notícias | NewsAPI |
| Build | Maven |

---

## ⚙️ Pré-requisitos

- Java 21+
- Maven 3.8+
- [Ollama](https://ollama.com/) instalado e rodando localmente
- Uma chave de API gratuita do [NewsAPI](https://newsapi.org/)

### Baixar o modelo Mistral (apenas na primeira vez)

```bash
ollama pull mistral:7b
```

---

## 🔧 Configuração

O projeto usa Spring Profiles para manter as credenciais fora do controle de versão.

### 1. Copie o arquivo de exemplo

```bash
cp src/main/resources/application-local.properties.example src/main/resources/application-local.properties
```

### 2. Preencha com seus valores

```properties
# application-local.properties
news.api.key=SUA_CHAVE_AQUI
news.api.base.url=https://newsapi.org/v2
news.api.default.country=br
news.api.url.content.top.headlines=/top-headlines

ollama.base.url=http://localhost:11434/api/generate
ollama.mistral.model=mistral:7b
```

> ⚠️ O arquivo `application-local.properties` está no `.gitignore` e nunca será commitado.

---

## ▶️ Rodando a Aplicação

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

Ou defina o profile ativo no IntelliJ em **Run > Edit Configurations > VM Options**:

```
-Dspring.profiles.active=local
```

---

## 📡 Endpoints

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/news/brief` | Busca as manchetes e retorna o resumo gerado pela IA em PT-BR |

---

## 📁 Estrutura do Projeto

```
src/main/java/com/kobra/news_ai/
├── client/
│   ├── NewsApiClient.java       # Busca manchetes na NewsAPI
│   └── OllamaClient.java        # Envia artigos ao Ollama para resumo
├── controller/
│   └── NewsBriefController.java
├── dto/
│   ├── Article.java
│   ├── NewsApiResponse.java
│   ├── OllamaRequest.java
│   └── OllamaResponse.java
└── service/
    └── NewsBriefService.java
```

---

## 👤 Autor

**João Gabriel** — [@joao-g4briel](https://github.com/joao-g4briel)  
LinkedIn: [linkedin.com/in/joao-g4briel](https://linkedin.com/in/joao-g4briel)

---

## 📄 Licença

Este projeto é open source e está disponível sob a [Licença MIT](LICENSE).
