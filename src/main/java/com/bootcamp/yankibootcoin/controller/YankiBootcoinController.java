package com.bootcamp.yankibootcoin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.yankibootcoin.model.BootcoinOrder;
import com.bootcamp.yankibootcoin.model.WalletBootcoin;
import com.bootcamp.yankibootcoin.service.YankiBootcoinCacheService;
import com.bootcamp.yankibootcoin.service.YankiBootcoinSendMoneyService;
import com.bootcamp.yankibootcoin.service.YankiBootcoinService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bootcoin")
public class YankiBootcoinController {
  
  @Autowired
  private YankiBootcoinService yankiBootcoinService;
  
  @Autowired
  private YankiBootcoinSendMoneyService yankiBootcoinSendMoneyService;
  
  @Autowired
  private YankiBootcoinCacheService yankiBootcoinCacheService;
  
  @PostMapping
  public Mono<WalletBootcoin> saveAccount(@RequestBody WalletBootcoin walletBootcoin){
    System.out.println(walletBootcoin);
    return yankiBootcoinService.saveAccount(walletBootcoin);
  }
  
  @PostMapping("/send")
  public Mono<WalletBootcoin> sendMoney(@RequestBody BootcoinOrder sendMoney){
    return yankiBootcoinSendMoneyService.sendMoney(sendMoney);
  }
  
  @GetMapping("/{id}")
  public Mono<WalletBootcoin> getById(@PathVariable("id") String id){
    return yankiBootcoinCacheService.findById(id);
  }

}
