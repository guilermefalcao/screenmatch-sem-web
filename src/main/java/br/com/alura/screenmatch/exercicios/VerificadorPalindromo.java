package br.com.alura.screenmatch.exercicios;

// Interface funcional para verificar se uma string é palíndromo
@FunctionalInterface
public interface VerificadorPalindromo {
    boolean verificarPalindromo(String str);
}