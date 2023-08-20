package com.example.demoessearch.config;



import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;

import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;

@Configuration
public class ElasticConfig {


    @Bean
    public ElasticsearchClient client(){
        RestClient httpClient = RestClient.builder(
                new HttpHost("localhost", 9200)
        ).build();
        ElasticsearchTransport transport = new RestClientTransport(httpClient,new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }
    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(ElasticsearchClient client, ElasticsearchConverter converter) {
        try {
            return new ElasticsearchTemplate(client, converter);
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
