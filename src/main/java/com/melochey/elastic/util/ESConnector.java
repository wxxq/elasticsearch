package com.melochey.elastic.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class ESConnector {
	//192.168.133.130
	public final static String SERVER_IP = "192.168.133.130";
	
	public final static int SERVER_PORT = 9200;
	
	private ESConnector(){
	}
	
	private static class SingleBuilderHolder{
		private final static RestClientBuilder builder = RestClient.builder(new HttpHost(SERVER_IP,SERVER_PORT, "http"));
	}
	
	
	public static RestHighLevelClient getClient(){
		RestHighLevelClient client = new RestHighLevelClient(SingleBuilderHolder.builder.build());
		return client;
	}
	
	
	
	public static void main(String[] args){
		
	}
}
