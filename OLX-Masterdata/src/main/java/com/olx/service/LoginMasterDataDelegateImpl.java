package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class LoginMasterDataDelegateImpl implements LoginMasterDataDelegate {

    @Autowired
    RestTemplate restTemplate;

    @Override
    @CircuitBreaker(name = "VALIDATE-TOKEN-CIRCUIT_BREAKER", fallbackMethod = "fallbackValidateToken")
    public ResponseEntity<Boolean> validateToken(String authToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", authToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            return restTemplate.exchange("http://api-gateway/olx/user/validate/token", HttpMethod.GET, entity, Boolean.class);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Boolean> fallbackValidateToken(String authToken, Throwable ex){
        System.out.println("Error in validating token -> " + ex.getMessage());
        return new ResponseEntity<>(false, HttpStatus.SERVICE_UNAVAILABLE);
    }


}
