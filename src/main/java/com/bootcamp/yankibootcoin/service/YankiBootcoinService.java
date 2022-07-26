package com.bootcamp.yankibootcoin.service;

import com.bootcamp.yankibootcoin.model.WalletBootcoin;

import reactor.core.publisher.Mono;

public interface YankiBootcoinService {
  
  Mono<WalletBootcoin> saveAccount(WalletBootcoin walletBootcoin);

}
