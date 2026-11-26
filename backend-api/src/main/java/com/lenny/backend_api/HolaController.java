package com.lenny.backend_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class HolaController {

    @GetMapping("/hola")
    public Map<String, String> saludar() {
        return Map.of(
                "mensaje", "Hola Lenny, tu API en Spring Boot esta funcionando",
                "status", "OK"
        );
    }
}