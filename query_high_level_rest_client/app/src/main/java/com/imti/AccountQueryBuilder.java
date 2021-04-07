package com.imti;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class AccountQueryBuilder {

  public QueryBuilder queryBuilder() {
    final var boolQueryBuilder = QueryBuilders.boolQuery();
    boolQueryBuilder
        .filter(QueryBuilders.rangeQuery("balance").gte(1000).lte(2000).includeLower(true)
            .includeUpper(false));
    return boolQueryBuilder;
  }
}
