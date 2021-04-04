package com.imti;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class App {

  private static final Logger LOGGER = Logger.getLogger(App.class.getName());
  private static final ElasticsearchConfig elasticsearchConfig = new ElasticsearchConfig();

  public static void main(String[] args) throws IOException {
    final var restHighLevelClient = elasticsearchConfig.restHighLevelClient();
    final var info = restHighLevelClient.info(RequestOptions.DEFAULT);
    LOGGER.log(Level.INFO, "Elasticsearch cluster name: {0} and version: {1} ",
        new String[]{info.getClusterName(),
            info.getVersion().getNumber()});
    restHighLevelClient.close();

    /*Sample code to insert data in Elasticsearch.
    curl -XPOST http://localhost:9200/posts/_doc -H "content-type: application/json" -d '{"name": "Imteyaz","school": "IIT Kgp"}'
    {"_index":"posts","_type":"_doc","_id":"VB-Gm3gBHJrXfjV4QISn","_version":1,"result":"created","_shards":{"total":2,"successful":1,"failed":0},"_seq_no":0,"_primary_term":1}*/

    final var searchRequest = new SearchRequest();
    searchRequest.indices("posts");
    final var searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.matchAllQuery());
    searchRequest.source(searchSourceBuilder);
    final var searchResponse = callSynchronously(searchRequest);
    LOGGER.log(Level.INFO, "Search response from synchronous call {0}", searchResponse);
    callASynchronously(searchRequest);
  }

  private static SearchResponse callSynchronously(final SearchRequest searchRequest)
      throws IOException {
    final RestHighLevelClient restHighLevelClient = elasticsearchConfig.restHighLevelClient();
    final var searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    restHighLevelClient.close();
    return searchResponse;
  }

  private static void callASynchronously(final SearchRequest searchRequest) {
    final RestHighLevelClient restHighLevelClient = elasticsearchConfig.restHighLevelClient();
    restHighLevelClient.searchAsync(searchRequest, RequestOptions.DEFAULT,
        new ActionListener<SearchResponse>() {
          @Override
          public void onResponse(final SearchResponse searchResponse) {
            LOGGER.log(Level.INFO, "Asynchronous call response  {0}", searchResponse);
            try {
              restHighLevelClient.close();
            } catch (IOException e) {
              LOGGER.log(Level.SEVERE, "Failed to close client {0}", e.getMessage());
            }
          }

          @Override
          public void onFailure(final Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to get results {0}", e.getMessage());
            try {
              restHighLevelClient.close();
            } catch (IOException ioe) {
              LOGGER.log(Level.SEVERE, "Failed to close client {0}", ioe.getMessage());
            }
          }
        });
  }
}
