package br.com.alura.screenmatch.exercicios;

import java.util.*;
import java.util.function.*;

/**
 * Classe para testar exercícios de Lambda Expressions
 * Para executar: altere ScreenmatchApplication para chamar ExerciciosLambda.executarTodos()
 */
public class ExerciciosLambda {

    public static void executarTodos() {
        System.out.println("=== EXERCÍCIOS DE LAMBDA EXPRESSIONS ===\n");
        
        exercicio1();
        exercicio2();
        exercicio3();
        exercicio4();
        exercicio5();
        exercicio6();
        exercicio7();
    }

    // 1 - Expressão lambda que multiplique dois números inteiros
    private static void exercicio1() {
        System.out.println("1. MULTIPLICAÇÃO DE DOIS NÚMEROS:");
        
        // Lambda usando interface funcional customizada
        Multiplicacao mult = (a, b) -> a * b;
        
        int resultado = mult.multiplicacao(5, 3);
        System.out.println("5 × 3 = " + resultado);
        System.out.println("10 × 7 = " + mult.multiplicacao(10, 7));
        System.out.println();
    }

    // 2 - Expressão lambda que verifique se um número é primo
    private static void exercicio2() {
        System.out.println("2. VERIFICAR SE NÚMERO É PRIMO:");
        
        // Lambda usando Predicate (interface funcional do Java)
        Predicate<Integer> ehPrimo = numero -> {
            if (numero <= 1) return false;
            if (numero <= 3) return true;
            if (numero % 2 == 0 || numero % 3 == 0) return false;
            
            // Verifica divisibilidade por números da forma 6k±1
            for (int i = 5; i * i <= numero; i += 6) {
                if (numero % i == 0 || numero % (i + 2) == 0) {
                    return false;
                }
            }
            return true;
        };
        
        int[] numeros = {2, 3, 4, 17, 25, 29};
        for (int num : numeros) {
            System.out.println(num + " é primo? " + ehPrimo.test(num));
        }
        System.out.println();
    }

    // 3 - Lambda que converta string para maiúsculas
    private static void exercicio3() {
        System.out.println("3. CONVERTER STRING PARA MAIÚSCULAS:");
        
        // Lambda usando Function (interface funcional do Java)
        Function<String, String> paraMaiuscula = str -> str.toUpperCase();
        
        String[] palavras = {"java", "lambda", "spring boot", "programação"};
        for (String palavra : palavras) {
            System.out.println("'" + palavra + "' → '" + paraMaiuscula.apply(palavra) + "'");
        }
        System.out.println();
    }

    // 4 - Lambda que verifique se string é palíndromo
    private static void exercicio4() {
        System.out.println("4. VERIFICAR SE STRING É PALÍNDROMO:");
        
        // Lambda usando interface funcional customizada
        VerificadorPalindromo verificador = str -> {
            // Remove espaços e converte para minúsculas
            String limpa = str.replaceAll("\\s+", "").toLowerCase();
            // Usa StringBuilder para reverter a string
            String reversa = new StringBuilder(limpa).reverse().toString();
            return limpa.equals(reversa);
        };
        
        String[] frases = {"arara", "java", "A man a plan a canal Panama", "hello", "level"};
        for (String frase : frases) {
            System.out.println("'" + frase + "' é palíndromo? " + verificador.verificarPalindromo(frase));
        }
        System.out.println();
    }

    // 5 - Lambda que multiplique cada número de uma lista por 3
    private static void exercicio5() {
        System.out.println("5. MULTIPLICAR LISTA POR 3:");
        
        List<Integer> numeros = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Lista original: " + numeros);
        
        // Lambda usando replaceAll (modifica a lista existente)
        numeros.replaceAll(n -> n * 3);
        
        System.out.println("Lista multiplicada por 3: " + numeros);
        System.out.println();
    }

    // 6 - Lambda que ordene lista de strings em ordem alfabética
    private static void exercicio6() {
        System.out.println("6. ORDENAR LISTA ALFABETICAMENTE:");
        
        List<String> nomes = new ArrayList<>(Arrays.asList("Maria", "João", "Ana", "Pedro", "Carlos"));
        System.out.println("Lista original: " + nomes);
        
        // Lambda usando sort com Comparator
        nomes.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        // Ou mais simples: nomes.sort(String::compareToIgnoreCase);
        
        System.out.println("Lista ordenada: " + nomes);
        System.out.println();
    }

    // 7 - Lambda que divida dois números com tratamento de exceção
    private static void exercicio7() {
        System.out.println("7. DIVISÃO COM TRATAMENTO DE EXCEÇÃO:");
        
        // Lambda usando interface BinaryOperator
        BinaryOperator<Double> divisao = (a, b) -> {
            if (b == 0) {
                throw new ArithmeticException("Divisão por zero não é permitida!");
            }
            return a / b;
        };
        
        // Testando divisões válidas
        try {
            System.out.println("10.0 ÷ 2.0 = " + divisao.apply(10.0, 2.0));
            System.out.println("15.0 ÷ 3.0 = " + divisao.apply(15.0, 3.0));
        } catch (ArithmeticException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
        // Testando divisão por zero
        try {
            System.out.println("10.0 ÷ 0.0 = " + divisao.apply(10.0, 0.0));
        } catch (ArithmeticException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
        System.out.println();
        System.out.println("=== FIM DOS EXERCÍCIOS ===");
    }
}