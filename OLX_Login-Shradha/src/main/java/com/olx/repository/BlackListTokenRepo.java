package com.olx.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olx.entity.LoginDocument;

public interface BlackListTokenRepo extends MongoRepository<LoginDocument, Integer> {

}
