package com.imti;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class AccountTest {

  @Test
  public void getAsMap() {
    final var account = new Account();
    account.setAccountNumber(1212323);
    account.setBalance(1234);
    account.setFirstname("Imteyaz");
    final var asMap = Account.getAsMap(account);
    Assertions.assertThat(asMap).isNotEmpty();
  }

}