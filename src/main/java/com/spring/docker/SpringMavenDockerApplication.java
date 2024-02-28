package com.spring.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@SpringBootApplication
public class SpringMavenDockerApplication {
    private final List<String> languages = Arrays.asList("Java", "Kotlin", "Golang", "Node.js");

    @GetMapping("/hello")
    public ResponseEntity<String> getHelloDocker() {
        return ResponseEntity.ok("Hello Docker!");
    }

    @GetMapping("/languages")
    public ResponseEntity<List<String>> getLanguages() {
        return ResponseEntity.ok(languages);
    }

    @GetMapping("/language/{name}")
    public ResponseEntity<?> getLanguageByName(@PathVariable String name) {
        List<String> findByName = languages.stream()
                .filter(language -> language.equalsIgnoreCase(name)).collect(Collectors.toList());

        if (findByName.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Resource not found for name: " + name);
        }

        return ResponseEntity.ok(findByName);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringMavenDockerApplication.class, args);
    }

}
