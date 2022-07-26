package com.bootcamp.yankibootcoin.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "wallet_bootcoin")
public class WalletBootcoin {

  @Id
  private String id;
  private String documentType;
  private String documentNumber;
  private String phoneNumber;
  private String email;
  private BigDecimal balance;

  @CreatedDate
  private LocalDate date;

  public void debitMoney(BigDecimal amount) {
    balance = balance.subtract(amount);
  }

  public void creditMoney(BigDecimal amount) {
    balance = balance.add(amount);
  }

}
