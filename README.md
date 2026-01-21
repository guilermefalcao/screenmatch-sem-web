# 🎬 ScreenMatch - Consumidor de API de Séries

Aplicação Spring Boot para busca e análise de séries de TV utilizando a API OMDB.

## 📋 Funcionalidades

- **Busca de Séries**: Consulta informações completas de séries via API OMDB
- **Análise de Episódios**: Lista todos os episódios de todas as temporadas
- **Top 5 Episódios**: Ranking dos episódios mais bem avaliados
- **Filtro por Data**: Busca episódios lançados a partir de um ano específico
- **Exercícios Lambda**: Exemplos práticos de programação funcional

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 4.0.1**
- **Jackson** (Deserialização JSON)
- **Maven** (Gerenciamento de dependências)
- **API OMDB** (Fonte de dados)

## 🚀 Como Executar

1. Clone o repositório
2. Configure sua API Key da OMDB no arquivo `Principal.java`
3. Execute: `mvn spring-boot:run`
4. Digite o nome da série desejada

## 📊 Exemplos de Uso

```
Digite o nome da série para busca: game of thrones
```

**Saída:**
- Informações gerais da série
- Lista completa de episódios
- Top 5 episódios mais bem avaliados
- Filtro por ano de lançamento

## 🎯 Conceitos Aplicados

### Lambda Expressions
- Sintaxe concisa para funções anônimas
- Programação funcional em Java

### Streams API
- Operações encadeadas em coleções
- `filter()`, `map()`, `flatMap()`, `sorted()`, `limit()`

### Tratamento de Dados
- Conversão JSON para objetos Java
- Tratamento de exceções (`NumberFormatException`, `DateTimeParseException`)
- Manipulação de datas com `LocalDate`

### Arquitetura
- Separação de responsabilidades
- Camada de serviço para consumo de API
- Models com Records para dados imutáveis

## 📁 Estrutura do Projeto

```
src/main/java/br/com/alura/screenmatch/
├── model/
│   ├── DadosSerie.java
│   ├── DadosTemporada.java
│   ├── DadosEpisodio.java
│   └── Episodio.java
├── service/
│   ├── ConsumoAPI.java
│   └── ConverteDados.java
├── principal/
│   └── Principal.java
├── exercicios/
│   ├── ExerciciosLambda.java
│   └── ExerciciosLambdaApplication.java
└── ScreenmatchApplication.java
```

## 🔧 Configuração SSL

Para ambientes corporativos, o projeto inclui configuração para bypass de certificados SSL.

## 📝 Exercícios Inclusos

Execute separadamente os exercícios de Lambda:
```bash
java br.com.alura.screenmatch.exercicios.ExerciciosLambdaApplication
```

---

**Desenvolvido durante o curso de Spring Boot - Alura** 🚀