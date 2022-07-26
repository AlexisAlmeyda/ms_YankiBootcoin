package com.bootcamp.yankibootcoin.producer;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.bootcamp.yankibootcoin.events.Event;
import com.bootcamp.yankibootcoin.events.EventType;
import com.bootcamp.yankibootcoin.events.WalletBootcoinEvent;
import com.bootcamp.yankibootcoin.model.WalletBootcoin;

@Component
public class YankiBootcoinProducer {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(YankiBootcoinProducer.class);
  
  @Value("${topic.wallet.name}")
  private String topicWallet;

  private final KafkaTemplate<String, Event<?>> kafkaTemplate;

  public YankiBootcoinProducer(@Qualifier("kafkaStringTemplate") KafkaTemplate<String, Event<?>> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendData(WalletBootcoin walletBootcoin) {

    WalletBootcoinEvent create = new WalletBootcoinEvent();
    create.setData(walletBootcoin);
    create.setId(UUID.randomUUID().toString());
    create.setDate(new Date());
    create.setType(EventType.CREATED);
    LOGGER.info("Create AccountWallet {}", create);
    this.kafkaTemplate.send(topicWallet, create);
  }

}
