package com.imti;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

public class AccountManagerTest {

  final RestHighLevelClient restHighLevelClient = new ElasticsearchConfig().restHighLevelClient();
  final AccountManager accountManager = new AccountManager(restHighLevelClient);

  @Test
  public void readAccountsWithValidDataFileShouldReturnAccounts() throws IOException {
    final URL resource = AccountManager.class.getClassLoader()
        .getResource("accounts.json");
    Assertions.assertThat(resource).isNotNull();
    final List<Account> accounts = accountManager.readAccounts(resource.getFile());
    Assertions.assertThat(accounts).isNotEmpty().hasSize(1000);
  }

  @Test
  public void writeAccountsValidAccountAndIndexNameShouldReturnBulkResponse() throws IOException {
    final URL resource = AccountManager.class.getClassLoader()
        .getResource("accounts.json");
    Assertions.assertThat(resource).isNotNull();
    final List<Account> accounts = accountManager.readAccounts(resource.getFile());
    final var bulkItemResponses = accountManager.writeAccounts(accounts, "accounts_test");
    Assertions.assertThat(bulkItemResponses.hasFailures()).isFalse();
    Assertions.assertThat(bulkItemResponses).isNotEmpty().hasSize(1000);
  }
}