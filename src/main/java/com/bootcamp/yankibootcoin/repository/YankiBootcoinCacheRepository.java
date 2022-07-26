package com.bootcamp.yankibootcoin.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.yankibootcoin.model.WalletBootcoin;

@Repository
public interface YankiBootcoinCacheRepository extends ReactiveMongoRepository<WalletBootcoin, String>{

}
