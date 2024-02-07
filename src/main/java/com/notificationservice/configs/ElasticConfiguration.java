package com.notificationservice.configs;
//
////import co.elastic.clients.elasticsearch.ElasticsearchClient;
////import co.elastic.clients.json.jackson.JacksonJsonpMapper;
////import co.elastic.clients.transport.ElasticsearchTransport;
////import co.elastic.clients.transport.rest_client.RestClientTransport;
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
////import org.elasticsearch.client.RestHighLevelClient;
////import org.springframework.context.annotation.Bean;
////import org.elasticsearch.client.RestHighLevelClient;
////import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
////import org.springframework.data.elasticsearch.client.RestClients;
////import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
////import org.springframework.data.elasticsearch.client.RestClients;
////import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
////import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.notificationservice.repository.elastic")
////@ComponentScan(basePackages = { "com.notificationservice.service" })
//public class ElasticConfiguration extends ElasticsearchConfiguration {
//
//
//
////    @Bean
////    @Override
////    public RestHighLevelClient elasticsearchClient() {
////        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
////                .connectedTo("localhost:9200")
////                .build();
////
////        return RestClients.create(clientConfiguration)
////                .rest();
////    }
//
//        @Bean
//    public ElasticsearchClient elasticsearchClient() {
//            RestClient restClient = RestClient
//                    .builder(HttpHost.create("http://localhost:9200"))
//                    .build();
//            ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
//            ElasticsearchClient client = new ElasticsearchClient(transport);
//    }
//
//    @Override
//    public ClientConfiguration clientConfiguration() {
//        return null;
//    }
//
////    @Bean
////    public ElasticsearchClient elasticsearchClient() {
////        RestClient restClient = RestClient.builder(
////                new HttpHost("localhost", 9200)).build();
////
////        ElasticsearchTransport transport = new RestClientTransport(
////                restClient, new JacksonJsonpMapper());
////
////        ElasticsearchClient client = new ElasticsearchClient(transport);
////        return client;
////    }
////
////    @Bean
////    @Override
////    public ClientConfiguration clientConfiguration() {
////        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
////                .connectedTo("localhost:9200")
////                .build();
////        return clientConfiguration;
////    }
//}
//
////@Configuration
////@EnableElasticsearchRepositories(basePackages = "com.notificationservice.repository")
////@ComponentScan(basePackages = { "com.notificationservice.service" })
////public class ElasticConfiguration extends ElasticsearchConfiguration {
////
////    @Bean
////    @Override
////    public ElasticsearchClient clientConfiguration() {
////        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
////                .connectedTo("localhost:9200")
////                .build();
////
////
////    }
////}

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.notificationservice.repository.elastic")
public class ElasticConfiguration extends AbstractElasticsearchConfiguration {

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration)
                .rest();
    }
}