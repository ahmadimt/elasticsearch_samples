/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.imti;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;

public class App {

  private static final Logger LOGGER = Logger.getLogger(App.class.getName());
  final ElasticsearchConfig elasticsearchConfig = new ElasticsearchConfig();
  final RestHighLevelClient restHighLevelClient = elasticsearchConfig.restHighLevelClient();

  public static void main(String[] args) throws IOException {
    LOGGER.log(Level.INFO, "Total document count with first name Kelley: {0}", new App().getCount());
  }


  public long getCount() throws IOException {
    final var countRequest = new CountRequest("accounts");
    countRequest.query(new AccountQueryBuilder().queryBuilder());
    final var countResponse = restHighLevelClient
        .count(countRequest, RequestOptions.DEFAULT);
    restHighLevelClient.close();
    return countResponse.getCount();
  }
}