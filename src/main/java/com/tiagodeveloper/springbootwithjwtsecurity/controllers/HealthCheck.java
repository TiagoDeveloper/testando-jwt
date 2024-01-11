package com.tiagodeveloper.springbootwithjwtsecurity.controllers;

import com.tiagodeveloper.springbootwithjwtsecurity.config.annotations.LogExecutionTime;
import com.tiagodeveloper.springbootwithjwtsecurity.domains.OverdueStatement;
import com.tiagodeveloper.springbootwithjwtsecurity.interceptors.ExtractUserIdInterceptor.ExtractUserId;
import com.tiagodeveloper.springbootwithjwtsecurity.utils.StatementUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthCheck {


    @GetMapping
    @LogExecutionTime
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String, String>> health() {
        final var response = new HashMap<String, String>();
        response.put("message", "OK");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/firstTen")
    @LogExecutionTime
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ExtractUserId(claimPropertyName="springbootwithjwtsecurity.claim-name", headerName = "authorization")
    public ResponseEntity<List<OverdueStatement>> healthTest() {
        return ResponseEntity.ok(StatementUtils.firstTen());
    }
}
