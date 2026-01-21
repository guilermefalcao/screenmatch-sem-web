# 🎬 ScreenMatch - Consumidor Avançado de API de Séries

Aplicação Spring Boot completa para busca, análise e estatísticas de séries de TV utilizando a API OMDB com programação funcional avançada.

## 📋 Funcionalidades Principais

### 🔍 **Busca e Análise de Séries**
- **Busca Completa**: Consulta informações detalhadas de séries via API OMDB
- **Análise de Episódios**: Lista todos os episódios de todas as temporadas
- **Top 10 Episódios**: Ranking dos episódios mais bem avaliados com debug visual
- **Busca Inteligente**: Localiza episódios por trecho do título (case-insensitive)
- **Filtro Temporal**: Episódios lançados a partir de um ano específico

### 📊 **Estatísticas Avançadas**
- **Avaliações por Temporada**: Média de avaliações agrupadas por temporada
- **Estatísticas Completas**: Análise estatística completa (min, max, média, soma, count)
- **Filtragem Inteligente**: Remove automaticamente dados inválidos ("N/A")
- **Formatação Brasileira**: Datas no formato dd/MM/yyyy

### 🛠️ **Programação Funcional**
- **Lambda Expressions**: Sintaxe concisa e funcional
- **Streams API Avançada**: Operações encadeadas complexas
- **Optional**: Tratamento seguro de valores nulos
- **Peek Debug**: Visualização do pipeline de streams em tempo real

## 🚀 Tecnologias Utilizadas

- **Java 17** - Linguagem principal
- **Spring Boot 4.0.1** - Framework de aplicação
- **Jackson** - Deserialização JSON
- **Maven** - Gerenciamento de dependências
- **API OMDB** - Fonte de dados de séries
- **Streams API** - Programação funcional
- **Optional** - Tratamento de nulos

## 🎯 Conceitos Avançados Implementados

### **Lambda Expressions & Method References**
```java
// Lambda expression
episodios.stream().filter(e -> e.getAvaliacao() > 8.0)

// Method reference
episodios.forEach(System.out::println)
```

### **Streams API Completa**
```java
// Pipeline complexo com peek para debug
dadosEpisodios.stream()
    .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
    .peek(e -> System.out.println("Filtrado: " + e))
    .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
    .limit(10)
    .collect(Collectors.toList());
```

### **Optional para Segurança**
```java
Optional<Episodio> episodio = episodios.stream()
    .filter(e -> e.getTitulo().contains("Winter"))
    .findFirst();
    
if (episodio.isPresent()) {
    System.out.println("Encontrado: " + episodio.get());
}
```

### **Agrupamento e Estatísticas**
```java
// Agrupamento por temporada
Map<Integer, Double> medias = episodios.stream()
    .collect(Collectors.groupingBy(
        Episodio::getTemporada,
        Collectors.averagingDouble(Episodio::getAvaliacao)
    ));

// Estatísticas completas
DoubleSummaryStatistics stats = episodios.stream()
    .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
```

## 📁 Arquitetura do Projeto

```
src/main/java/br/com/alura/screenmatch/
├── 📂 model/                    # Modelos de dados
│   ├── 📄 DadosSerie.java      # Record para dados da série
│   ├── 📄 DadosTemporada.java  # Record para dados da temporada
│   ├── 📄 DadosEpisodio.java   # Record para dados do episódio
│   └── 📄 Episodio.java        # Classe completa com getters/setters
├── 📂 service/                  # Camada de serviços
│   ├── 📄 ConsumoAPI.java      # Cliente HTTP com SSL bypass
│   └── 📄 ConverteDados.java   # Conversor JSON para objetos
├── 📂 principal/                # Lógica principal
│   └── 📄 Principal.java       # Menu e processamento principal
├── 📂 exercicios/               # Exercícios separados
│   ├── 📄 ExerciciosLambda.java           # Exemplos de lambda
│   └── 📄 ExerciciosLambdaApplication.java # Executor independente
└── 📄 ScreenmatchApplication.java # Classe principal Spring Boot
```

## 🚀 Como Executar

### **Projeto Principal (Séries)**
```bash
# Clone o repositório
git clone https://github.com/guilermefalcao/screenmatch-sem-web.git

# Entre na pasta
cd screenmatch

# Execute a aplicação
mvn spring-boot:run
```

### **Exercícios de Lambda (Separado)**
```bash
# Execute os exercícios independentemente
java br.com.alura.screenmatch.exercicios.ExerciciosLambdaApplication
```

## 📊 Exemplo de Uso Completo

```
🎬 Digite o nome da série: Game of Thrones

📺 Série encontrada: Game of Thrones (8 temporadas, 9.2⭐)

🏆 TOP 10 EPISÓDIOS:
1. The Rains of Castamere (9.9⭐)
2. Battle of the Bastards (9.9⭐)
3. The Winds of Winter (9.9⭐)
...

📊 AVALIAÇÕES POR TEMPORADA:
Temporada 1: 8.82
Temporada 2: 8.63
...

🔍 Busca: "winter" → Winter Is Coming encontrado!

📅 Episódios desde 2015: 67 episódios listados

📈 ESTATÍSTICAS GERAIS:
• Episódios avaliados: 73
• Menor avaliação: 4.0
• Maior avaliação: 9.9
• Média geral: 8.34
```

## 🔧 Configurações Especiais

### **SSL Bypass (Ambiente Corporativo)**
O projeto inclui configuração automática para bypass de certificados SSL em ambientes corporativos.

### **Tratamento de Dados**
- **Valores "N/A"**: Convertidos automaticamente para 0.0
- **Datas inválidas**: Tratadas como null com validação
- **Exceções**: Capturadas e tratadas graciosamente

## 🎓 Conceitos de Aprendizado

### **Streams API**
- Operações intermediárias: `filter()`, `map()`, `sorted()`, `peek()`
- Operações terminais: `forEach()`, `collect()`, `findFirst()`
- Collectors: `toList()`, `toSet()`, `groupingBy()`, `summarizingDouble()`

### **Programação Funcional**
- Lambda expressions e method references
- Interfaces funcionais (Predicate, Function, Consumer)
- Programação declarativa vs imperativa

### **Tratamento de Dados**
- Optional para evitar NullPointerException
- Conversão e validação de tipos
- Formatação e apresentação de dados

---

**🎯 Projeto desenvolvido durante o curso de Spring Boot - Alura**  
**🚀 Foco em programação funcional e boas práticas Java**