package com.bootcamp.yankibootcoin.service;

import com.bootcamp.yankibootcoin.model.BootcoinOrder;
import com.bootcamp.yankibootcoin.model.WalletBootcoin;

import reactor.core.publisher.Mono;

public interface YankiBootcoinSendMoneyService {
  
  Mono<WalletBootcoin> sendMoney(BootcoinOrder sendMoney);

}
