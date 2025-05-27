package com.GreenEnergy.soporteDeProblemas.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class ClienteClient {

    private final WebClient webClient;

    public ClienteClient(@Value("${clientes-service.url}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Map<String, Object> buscarClientePorUsuario(String username) {
        return this.webClient.get()
            .uri("/buscarPorUsername/{username}", username)
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new RuntimeException("Usuario no encontrado")))
            .bodyToMono(Map.class)
            .block();
    }
}
