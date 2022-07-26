package com.bootcamp.yankibootcoin.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.yankibootcoin.model.WalletBootcoin;

import reactor.core.publisher.Mono;

@Repository
public interface YankiBootcoinRepository extends ReactiveMongoRepository<WalletBootcoin, String>{

  Mono<WalletBootcoin> findByPhoneNumber(String phoneNumber);
  
}
