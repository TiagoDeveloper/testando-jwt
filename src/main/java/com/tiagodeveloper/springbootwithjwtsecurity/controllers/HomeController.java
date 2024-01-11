package com.tiagodeveloper.springbootwithjwtsecurity.controllers;

import com.tiagodeveloper.springbootwithjwtsecurity.config.properties.SecurityMappingConfig;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private SecurityMappingConfig securityMappingConfig;

    @Autowired
    private HttpSession httpSession;

    @GetMapping
    public ResponseEntity<Map<String, String>> init() {
        final var response = new HashMap<String, String>();
        response.put("message", "Hello");
        securityMappingConfig.mapping().forEach(e -> {
            System.out.println(e.getPath());
            System.out.println(Arrays.toString(e.getRoles()));
        });

        final Map<String, String> map = new HashMap<>();
        var countLocal = Optional.ofNullable(httpSession.getAttribute("count")).map(e -> Integer.parseInt(e.toString())).orElse(0);
        httpSession.setAttribute("count", ++countLocal);
        map.put("mensagem", "caiu aqui");
        map.put("count", Optional.ofNullable(httpSession.getAttribute("count")).map(Object::toString).orElse(null));
        map.put("id", httpSession.getId());

        return ResponseEntity.ok(map);
    }
 }
