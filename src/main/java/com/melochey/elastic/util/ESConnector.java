package com.melochey.elastic.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class ESConnector {
	
	private ESConnector(){
	}
	
	private static class SingleBuilderHolder{
		private final static RestClientBuilder builder = RestClient.builder(new HttpHost("172.16.24.207", 9200, "http"));
	}
	
	
	public static RestHighLevelClient getClient(){
		RestHighLevelClient client = new RestHighLevelClient(SingleBuilderHolder.builder.build());
		return client;
	}
	
	
	
	public static void main(String[] args){
		
	}
}
