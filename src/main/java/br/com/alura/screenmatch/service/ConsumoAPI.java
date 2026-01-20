package br.com.alura.screenmatch.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

// Classe responsável por consumir APIs externas
public class ConsumoAPI {

    // Método que faz requisição HTTP e retorna os dados em formato JSON
    // Parâmetro: endereco - URL da API que será consultada
    public String obterDados(String endereco) {
        HttpClient client;
        
        try {
            // Cria um TrustManager que aceita todos os certificados (para desenvolvimento)
            TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() { return null; }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                    public void checkServerTrusted(X509Certificate[] certs, String authType) { }
                }
            };
            
            // Configura o SSLContext para aceitar todos os certificados
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            
            // Cria um cliente HTTP que ignora validação SSL
            client = HttpClient.newBuilder()
                    .sslContext(sslContext)
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();
                    
        } catch (Exception e) {
            // Se falhar, usa cliente padrão
            client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();
        }
        
        // Constrói a requisição HTTP com a URL fornecida
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))  // Define a URI da requisição
                .timeout(Duration.ofSeconds(30))  // Timeout para a requisição
                .build();
        
        // Variável para armazenar a resposta da requisição
        HttpResponse<String> response = null;
        
        try {
            // Envia a requisição e aguarda a resposta
            // BodyHandlers.ofString() converte a resposta para String
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            // Trata erros de entrada/saída (problemas de rede, conexão, etc.)
            throw new RuntimeException("Erro de conexão: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            // Trata interrupções durante a execução da requisição
            Thread.currentThread().interrupt();
            throw new RuntimeException("Requisição interrompida: " + e.getMessage(), e);
        }

        // Extrai o corpo da resposta (dados JSON)
        String json = response.body();
        return json;
    }
}