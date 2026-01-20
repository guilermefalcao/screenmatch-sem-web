package br.com.alura.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Classe que implementa a conversão de dados JSON usando Jackson
public class ConverteDados implements IConverteDados {

    // ObjectMapper do Jackson para fazer a conversão JSON <-> Objeto Java
    private ObjectMapper mapper = new ObjectMapper();

    // Implementação do método da interface para converter JSON em objeto
    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            // Lê o JSON e tenta transformar na classe que foi passada como parâmetro
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            // Se houver erro na conversão, lança uma RuntimeException
            throw new RuntimeException(e);
        }
    }
}