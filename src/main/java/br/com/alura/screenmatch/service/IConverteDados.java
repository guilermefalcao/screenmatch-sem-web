package br.com.alura.screenmatch.service;

// Interface que define o contrato para conversão de dados JSON
public interface IConverteDados {

    // Método genérico que converte JSON em objeto Java
    // T - tipo genérico que será retornado
    // json - string JSON a ser convertida
    // classe - classe do objeto que será criado
    <T> T obterDados(String json, Class<T> classe);
}
