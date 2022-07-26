package com.bootcamp.yankibootcoin.events;

import com.bootcamp.yankibootcoin.model.WalletBootcoin;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WalletBootcoinEvent  extends Event<WalletBootcoin>{
  

}
