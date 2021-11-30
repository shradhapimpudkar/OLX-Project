package com.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.dto.BlackListedToken;

public interface BlackListTokenRepo extends JpaRepository<BlackListedToken, Integer> {

	BlackListedToken findBlackListedToken(String authToken);
}
