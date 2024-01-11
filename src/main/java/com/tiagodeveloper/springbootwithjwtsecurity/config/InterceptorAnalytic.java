package com.tiagodeveloper.springbootwithjwtsecurity.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
import java.util.Optional;

@Aspect
@Component
public class InterceptorAnalytic {

    @Autowired
    private ObjectMapper objectMapper;

    @Around("@annotation(com.tiagodeveloper.springbootwithjwtsecurity.config.annotations.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest requestContext = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var args = Optional.ofNullable(joinPoint.getArgs()).map(e -> {
            try {
                return e.length > 0 ? objectMapper.writeValueAsString(e[0]) : null;
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }).orElse(null);

        return joinPoint.proceed();
    }

}
