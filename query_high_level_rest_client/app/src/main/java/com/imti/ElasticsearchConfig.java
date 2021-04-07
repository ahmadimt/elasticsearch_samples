package com.imti;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticsearchConfig {

  public RestHighLevelClient restHighLevelClient() {
    RestClientBuilder restClientBuilder = RestClient.builder(
        new HttpHost("localhost", 9200, "http"));
    return new RestHighLevelClient(restClientBuilder);
  }

}
