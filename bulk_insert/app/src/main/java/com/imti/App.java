/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.imti;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.elasticsearch.client.RestHighLevelClient;

public class App {

  private static final Logger LOGGER = Logger.getLogger(App.class.getName());

  public static void main(String[] args) throws IOException {
    final RestHighLevelClient restHighLevelClient = new ElasticsearchConfig().restHighLevelClient();
    final AccountManager accountManager = new AccountManager(restHighLevelClient);
    final URL resource = AccountManager.class.getClassLoader()
        .getResource("accounts.json");
    final List<Account> accounts = accountManager.readAccounts(resource.getFile());
    final var bulkItemResponses = accountManager.writeAccounts(accounts, "accounts_test");
    LOGGER.log(Level.INFO, "Number of items inserted  {0}", bulkItemResponses.getItems().length);
    restHighLevelClient.close();
  }
}
