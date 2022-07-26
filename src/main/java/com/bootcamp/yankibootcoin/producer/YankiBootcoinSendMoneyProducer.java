package com.bootcamp.yankibootcoin.producer;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.bootcamp.yankibootcoin.events.PaymentMethod;
import com.bootcamp.yankibootcoin.events.SendMoneyEvent;
import com.bootcamp.yankibootcoin.events.WalletBootcoinSendMoneyEvent;
import com.bootcamp.yankibootcoin.model.BootcoinOrder;

@Component
public class YankiBootcoinSendMoneyProducer {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(YankiBootcoinSendMoneyProducer.class);
  
  private final KafkaTemplate<String, SendMoneyEvent<?>> kafkaTemplate;

  public YankiBootcoinSendMoneyProducer(@Qualifier("kafkaStringTemplateSend") KafkaTemplate<String, SendMoneyEvent<?>> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }
  
  public void sendMoney(BootcoinOrder bootcoinOrder) {

    WalletBootcoinSendMoneyEvent create = new WalletBootcoinSendMoneyEvent();
    create.setData(bootcoinOrder);
    create.setId(UUID.randomUUID().toString());
    create.setDate(new Date());
    create.setPaymentMethod(PaymentMethod.YANKI);
    LOGGER.info("Create AccountWallet {}", create);
    this.kafkaTemplate.send("BOOTCOIN_SENDMONEY", create);
  }

}
