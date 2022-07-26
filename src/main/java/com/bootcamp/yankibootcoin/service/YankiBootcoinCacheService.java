package com.bootcamp.yankibootcoin.service;

import com.bootcamp.yankibootcoin.model.WalletBootcoin;
import reactor.core.publisher.Mono;

public interface YankiBootcoinCacheService {
  
  Mono<WalletBootcoin> findById(String id);

}
