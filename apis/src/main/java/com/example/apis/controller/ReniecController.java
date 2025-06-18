package com.example.apis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.apis.service.ReniecService;

@RestController
@RequestMapping("/api/reniec")
public class ReniecController {

    @Autowired
    private ReniecService reniecService;

    @GetMapping("/dni/{numero}")
    public String getDatosPorDni(@PathVariable String numero) {
        return reniecService.consultarPorDni(numero);
    }

    @GetMapping("/factiliza/dni/{numero}")
    public String getDatosPorDniFactiliza(@PathVariable String numero) {
        return reniecService.consultarPorDniFactiliza(numero);
    }

}
