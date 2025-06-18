package com.example.apis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.apis.service.SunatService;

@RestController
@RequestMapping("/api/sunat")
public class SunatController {

    private final SunatService sunatService;

    public SunatController(SunatService sunatService) {
        this.sunatService = sunatService;
    }

    @GetMapping("/ruc/{numero}")
    public ResponseEntity<String> getDatosPorRuc(@PathVariable String numero) {
        String response = sunatService.consultarPorRuc(numero);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/factiliza/ruc/{numero}")
    public ResponseEntity<String> getDatosPorRucFactiliza(@PathVariable String numero) {
        String response = sunatService.consultarPorRucFactiliza(numero);
        return ResponseEntity.ok(response);
    }

}
