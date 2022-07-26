package com.bootcamp.yankibootcoin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.yankibootcoin.model.WalletBootcoin;
import com.bootcamp.yankibootcoin.producer.YankiBootcoinProducer;
import com.bootcamp.yankibootcoin.repository.YankiBootcoinRepository;

import reactor.core.publisher.Mono;

@Service
public class YankiBootcoinServiceImpl implements YankiBootcoinService{
  
  private static final Logger LOGGER = LoggerFactory.getLogger(YankiBootcoinServiceImpl.class);
  
  @Autowired
  private YankiBootcoinRepository yankiBootcoinRepository;
  
  @Autowired
  private YankiBootcoinProducer yankiBootcoinProducer;

  @Override
  public Mono<WalletBootcoin> saveAccount(WalletBootcoin walletBootcoin) {
    return yankiBootcoinRepository.findByPhoneNumber(walletBootcoin.getPhoneNumber())
        .flatMap(accountFound -> Mono.error(new RuntimeException("The cell phone number is already registered.")))
        .then(Mono.just(walletBootcoin))
        .flatMap(createAccount -> yankiBootcoinRepository.save(createAccount).map(yankiBootcoin -> {
          yankiBootcoinProducer.sendData(walletBootcoin);
            LOGGER.info("Request, {}", walletBootcoin);
            return yankiBootcoin;
        }));
  }

}
