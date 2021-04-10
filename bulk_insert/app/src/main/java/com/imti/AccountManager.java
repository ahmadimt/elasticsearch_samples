package com.imti;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * The type Account manager.
 */
public class AccountManager {

  private final RestHighLevelClient restHighLevelClient;

  /**
   * Instantiates a new Account manager.
   *
   * @param restHighLevelClient the rest high level client
   */
  public AccountManager(final RestHighLevelClient restHighLevelClient) {
    this.restHighLevelClient = restHighLevelClient;
  }

  /**
   * Read data from the file and return as a List<Account>.
   *
   * @param fileName the file name
   * @return the list
   * @throws IOException the io exception
   */
  public List<Account> readAccounts(String fileName) throws IOException {
    final File file = new File(fileName);
    final Account[] accounts = new ObjectMapper().readValue(file, Account[].class);
    return Arrays.asList(accounts);
  }

  /**
   * Write accounts data into Elasticsearch
   *
   * @param accounts  the accounts
   * @param indexName the index name
   * @return the bulk response
   * @throws IOException the io exception
   */
  public BulkResponse writeAccounts(List<Account> accounts, String indexName) throws IOException {
    final var bulkRequest = new BulkRequest();
    accounts.forEach(account -> {
      final var indexRequest = new IndexRequest(indexName);
      indexRequest.source(Account.getAsMap(account));
      bulkRequest.add(indexRequest);
    });
    return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
  }
}
