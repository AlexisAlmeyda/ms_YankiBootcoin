package com.bootcamp.yankibootcoin.service;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.yankibootcoin.model.BootcoinOrder;
import com.bootcamp.yankibootcoin.model.WalletBootcoin;
import com.bootcamp.yankibootcoin.producer.YankiBootcoinSendMoneyProducer;
import com.bootcamp.yankibootcoin.repository.YankiBootcoinRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class YankiBootcoinSendMoneyServiceImpl implements YankiBootcoinSendMoneyService{
  
  private static final Logger LOGGER = LoggerFactory.getLogger(YankiBootcoinSendMoneyServiceImpl.class);
  
  @Autowired
  private YankiBootcoinRepository yankiBootcoinRepository;
  
  @Autowired
  private YankiBootcoinSendMoneyProducer yankiBootcoinSendMoneyProducer;

  @Override
  public Mono<WalletBootcoin> sendMoney(BootcoinOrder sendMoney) {
    
    LOGGER.info("Start transaction with ID {}", sendMoney.getTransactionNumber());
    LOGGER.info("Print sendmoney: {}", sendMoney.toString());
    
    Mono<WalletBootcoin> yankiSenderMono= yankiBootcoinRepository.findByPhoneNumber(sendMoney.getSenderId())
        .switchIfEmpty(Mono.error(new RuntimeException("This account doesn't exist")))
        .filter(walletYanki -> sendMoney.getAmount().compareTo(walletYanki.getBalance()) <= 0)
        .doOnNext(w -> LOGGER.info("Is printed after checking the balance {}", w))
        .switchIfEmpty(Mono.error(new RuntimeException("Insufficient balance!!!!!")));
    
    Mono<WalletBootcoin> yankiReceiverMono = yankiBootcoinRepository.findByPhoneNumber(sendMoney.getReceiverId())
        .switchIfEmpty(Mono.error(new RuntimeException("This account doesn't exist")));
    
    return Mono.zip(yankiSenderMono,yankiReceiverMono,(yankiSender, yankiReceiver) -> {
      BigDecimal money = sendMoney.getAmount();
      yankiSender.debitMoney(money);
      yankiReceiver.creditMoney(money);
      
      return Stream.of(yankiSender,yankiReceiver);
    }).flatMapMany(yankiBootcoin ->{
      yankiBootcoinSendMoneyProducer.sendMoney(sendMoney);
      return yankiBootcoinRepository.saveAll(Flux.fromStream(yankiBootcoin));
    }).filter(yankiBootcoin -> yankiBootcoin.getPhoneNumber().equals(sendMoney.getSenderId()))
        .elementAt(0);
    
  }

}
