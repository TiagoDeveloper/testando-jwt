package com.tiagodeveloper.springbootwithjwtsecurity.controllers;

import com.tiagodeveloper.springbootwithjwtsecurity.config.annotations.LogExecutionTime;
import com.tiagodeveloper.springbootwithjwtsecurity.interceptors.ExtractUserIdInterceptor.ExtractUserId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/calc")
public class BigDecimalTestController {

    private final BigDecimal tax = BigDecimal.valueOf(0.10);

    @PostMapping
    @LogExecutionTime
    public ResponseEntity<Map<String, BigDecimal>> calc(@RequestBody Map<String, BigDecimal> body) {
        final BigDecimal price = body.get("price").setScale(2, RoundingMode.DOWN);
        var pricePlusTax = price.multiply(tax).add(price).setScale(2, RoundingMode.DOWN);
        final Map<String, BigDecimal> response = new HashMap<>();
            response.put("price", price);
            response.put("tax", tax);
            response.put("pricePlusTax", pricePlusTax);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<Map<String, BigDecimal>> calc2(@RequestBody BigDecimal body, @PathVariable Long id) {
        final BigDecimal price = body.setScale(2, RoundingMode.DOWN);
        var pricePlusTax = price.multiply(tax).add(price).setScale(2, RoundingMode.DOWN);
        final Map<String, BigDecimal> response = new HashMap<>();
        response.put("price", price);
        response.put("tax", tax);
        response.put("pricePlusTax", pricePlusTax);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/test")
    @LogExecutionTime
    @ExtractUserId(claimPropertyName="sub", headerName = "authorization")
    public ResponseEntity<Map<String, BigDecimal>> calc(@RequestBody CalcRecord body) {
        final BigDecimal price = body.price.setScale(2, RoundingMode.DOWN);
        var pricePlusTax = price.multiply(tax).add(price).setScale(2, RoundingMode.DOWN);
        final Map<String, BigDecimal> response = new HashMap<>();
        response.put("price", price);
        response.put("tax", tax);
        response.put("pricePlusTax", pricePlusTax);
        return ResponseEntity.ok(response);
    }


    record CalcRecord(BigDecimal price, BigDecimal tax, BigDecimal pricePlusTax){

    }

}
