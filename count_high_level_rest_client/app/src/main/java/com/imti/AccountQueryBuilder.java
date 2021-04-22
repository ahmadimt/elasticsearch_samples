package com.imti;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class AccountQueryBuilder {

  public QueryBuilder queryBuilder() {
    return QueryBuilders.matchQuery("firstname.keyword", "Kelley");
  }
}
