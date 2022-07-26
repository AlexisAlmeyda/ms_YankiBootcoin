package com.bootcamp.yankibootcoin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bootcamp.yankibootcoin.model.WalletBootcoin;
import com.bootcamp.yankibootcoin.repository.YankiBootcoinCacheRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class YankiBootcoinCacheServiceImpl implements YankiBootcoinCacheService{
  
  final Logger LOGGER = LoggerFactory.getLogger(YankiBootcoinCacheServiceImpl.class);
  
  @Autowired
  private YankiBootcoinCacheRepository yankiBootcoinCacheRepository;

  @Override
  @Cacheable(value = "userCache")
  public Mono<WalletBootcoin> findById(String id) {
    LOGGER.info("Getting user with ID {}.", id);
    return yankiBootcoinCacheRepository.findById(id);
  }

}
