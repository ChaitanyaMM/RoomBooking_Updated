package com.roomsbooking.Configuration;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.roomsbooking" })
@PropertySource("classpath:com/roomsbooking/Configuration/config.properties")

public class ApplicationConfig {
	@Value("${mongodb.url}")
	private String mongodbUrl;
	//MM DB
	@Value("${mongodb.db}")
	private String defaultDb;
	@Value("${mongodb.port}")
	private String  port;
	@Value("${mongodb.username}")
	private String username;
	@Value("${mongodb.password}")
	private String password;
	@Value("${mongodb.serverAddress}")
	private String  host;

 
	
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {

		List<ServerAddress> seeds = new ArrayList<ServerAddress>();
//		seeds.add(new ServerAddress("192.168.1.119", 27017));
//		seeds.add(new ServerAddress("192.168.1.120", 27017));
		seeds.add(new ServerAddress(mongodbUrl, 27017));
		MongoClientOptions mongoOptions = 
			new MongoClientOptions.Builder().maxWaitTime(1500 * 60 * 5).socketKeepAlive(true).socketTimeout(1500)
			.connectionsPerHost(5).build();
		MongoClient mongo = new MongoClient(seeds, mongoOptions);

		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo, defaultDb);	
		return new MongoTemplate(mongoDbFactory);
	}
	//To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
//	@Bean
//	public GridFsTemplate gridFsTemplate() throws Exception {
//	    return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
//	}
//	@Bean
//	public MultipartResolver multipartResolver() {
//	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//	    multipartResolver.setMaxUploadSize(500000000);
//	    multipartResolver.setDefaultEncoding("utf-8");
//	    return multipartResolver;
//	}

//	  public String getDatabaseName() {
//	    return "defaultDb";
//	  }
//
//	  @Bean
//	  public Mongo mongo() throws Exception {
//	    return new MongoClient(singletonList(new ServerAddress("127.0.0.1", 27017)),
//	      singletonList(MongoCredential.createCredential("username", "defaultDb", "password".toCharArray())));
//		  //return new MongoClient(this.host, this.port);
//	  }
 
//	
//	private MongoClientOptions singletonList(MongoCredential createCredential) {
//
//		return null;
//	}
//	private List<ServerAddress> singletonList(ServerAddress serverAddress) throws UnknownHostException {
//		List<ServerAddress> seeds = new ArrayList<ServerAddress>();
//		seeds.add(new ServerAddress(mongodbUrl, 27017));		
//		return seeds;
//	}
	 
@Bean
public MongoDbFactory mongoDbFactory() throws Exception {

    // Set credentials      
    MongoCredential credential = MongoCredential.createCredential(this.username, this.defaultDb, this.password.toCharArray());
    ServerAddress serverAddress = new ServerAddress(mongodbUrl, 27017);
     // Mongo Client
    MongoClient mongoClient = new MongoClient(serverAddress,Arrays.asList(credential)); 

    // Mongo DB Factory
    SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(
            mongoClient, this.defaultDb);

    return simpleMongoDbFactory;
}
	/* Rest Template */
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }
	
	private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(2000);
        factory.setConnectTimeout(2000);
        return factory;
    }
//	@Bean
//	public CascadingMongoEventListener CascadingMongoEventListener() {
//	    return new CascadingMongoEventListener();
//	}
//	 
//	
//	
//	
//	
//
 }
