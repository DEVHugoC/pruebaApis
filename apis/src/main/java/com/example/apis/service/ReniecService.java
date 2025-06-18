package com.example.apis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Collections;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ReniecService {

    @Value("${reniec.api.url}")
    private String reniecApiUrl;

    @Value("${apis.token}")
    private String token;

    private final RestTemplate restTemplate = new RestTemplate();

    public String consultarPorDni(String dni) {
        String url = reniecApiUrl + "?numero=" + dni;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, String.class);

            return response.getBody();

        } catch (HttpClientErrorException.NotFound e) {
            return "{\"error\": \"DNI no encontrado\"}";

        } catch (HttpClientErrorException e) {
            return "{\"error\": \"Error cliente: " + e.getStatusCode() + "\"}";

        } catch (Exception e) {
            return "{\"error\": \"Error interno en el servidor\"}";
        }
    }
}
