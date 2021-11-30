package com.olx.service;

import org.springframework.http.ResponseEntity;

public interface LoginMasterDataDelegate {
    ResponseEntity<Boolean> validateToken(String authToken);
}
