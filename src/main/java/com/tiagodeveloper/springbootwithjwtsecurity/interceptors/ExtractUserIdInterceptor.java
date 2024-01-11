package com.tiagodeveloper.springbootwithjwtsecurity.interceptors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class ExtractUserIdInterceptor {

    @Autowired
    private Environment environment;
    @Autowired
    private ObjectMapper objectMapper;
    @Around(value = "@annotation(extractUserId)")
    public Object extractUserIdInterceptor(ProceedingJoinPoint proceedingJoinPoint, ExtractUserId extractUserId) {
        try {
            var value = environment.getProperty(extractUserId.claimPropertyName());

            var requestAttributes = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            var jwt = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader(extractUserId.headerName());

            var claimValue = extract(jwt, value);

            log.info("valor do header: {}", jwt);
            log.info("valor do claimValue: {}", claimValue);
            Arrays.stream(proceedingJoinPoint.getArgs()).forEach(e ->
                    log.info("valor da request: {}", e)
            );

            var response = (ResponseEntity<?>)proceedingJoinPoint.proceed();

            log.info("valor do response: {}", objectMapper.writeValueAsString(response.getBody()));

            return response;

        }catch(Throwable ex) {
            log.error("Deu ruim", ex.fillInStackTrace());
            throw new RuntimeException("Deu ruim");
        }
    }

    private String extract(String jwt, String claimName) throws IOException {

        var tokens = jwt.split("\\.");
        var decoded = Base64.getDecoder().decode(tokens[1].getBytes());
        var result = objectMapper.readValue(decoded, new TypeReference<HashMap<String,String>>() {});
        return result.get(claimName);
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExtractUserId {
        String claimPropertyName();
        String headerName();
    }

}
