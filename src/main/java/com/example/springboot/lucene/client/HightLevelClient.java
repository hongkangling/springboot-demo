package com.example.springboot.lucene.client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author linghongkang
 * @description:
 * @create: 2018-12-06 15:57
 **/
public class HightLevelClient {


    public static RestHighLevelClient  getClient(){
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
    }

}
