package com.example.apis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Collections;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class SunatService {

    @Value("${sunat.api.url}")
    private String sunatApiUrl;

    @Value("${apis.token}")
    private String token;

    @Value("${factiliza.sunat.api.url}")
    private String factilizaApiRucUrl;

    @Value("${factiliza.api.token}")
    private String factilizaToken;

    @Value("${factiliza.sunat.api.representante.url}")
    private String factilizaApiRepresentanteUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String consultarPorRuc(String ruc) {
        String url = sunatApiUrl + "?numero=" + ruc;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return "{\"error\":\"" + e.getStatusCode() + " - " + e.getMessage() + "\"}";
        } catch (Exception e) {
            return "{\"error\":\"500 - Error interno del servidor\"}";
        }
    }

    public String consultarPorRucFactiliza(String ruc) {
        String url = factilizaApiRucUrl + "/" + ruc;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(factilizaToken);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return "{\"error\":\"" + e.getStatusCode() + " - " + e.getMessage() + "\"}";
        } catch (Exception e) {
            return "{\"error\":\"500 - Error interno del servidor\"}";
        }
    }

    public String consultarRepresentantePorRucFactiliza(String ruc) {
        String url = factilizaApiRepresentanteUrl + "/" + ruc;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(factilizaToken);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return "{\"error\":\"" + e.getStatusCode() + " - " + e.getMessage() + "\"}";
        } catch (Exception e) {
            return "{\"error\":\"500 - Error interno del servidor\"}";
        }
    }

}
