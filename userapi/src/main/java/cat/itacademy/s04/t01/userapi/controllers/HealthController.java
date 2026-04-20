package cat.itacademy.s04.t01.userapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public HealthStatus checkHealth() {
        return new HealthStatus("OK");
    }
}
