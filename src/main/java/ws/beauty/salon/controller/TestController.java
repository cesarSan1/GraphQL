package ws.beauty.salon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TestController {
   
    @GetMapping("/test")
    public String publicTest() {
        return "Servidor funcionando sin autenticación";
    }

    @GetMapping("/api/v1/stylist/test-admin")
    public String adminTest() {
        return "Acceso permitido solo para ADMIN";
    } 
}
