package com.atball.der.search.controller;

import com.atball.der.search.config.ElasticsearchConfig;
import com.atball.der.search.dto.SearchParam;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.naming.directory.SearchResult;
import java.io.IOException;

/**
 * DSL 语句查看resource中的DSL.json文件
 */

public class SearchController {

    @Autowired
    private RestHighLevelClient client;

    public SearchResult search(SearchParam param) {

        // 动态构建出查询需要的DSL语句



        SearchResult searchResult = null;

        SearchRequest searchRequest = new SearchRequest();

        try {
            SearchResponse response = client.search(searchRequest, ElasticsearchConfig.COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return searchResult;

    }

}
