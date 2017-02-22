package com.roomsbooking.DaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import com.roomsbooking.Dao.HelloDao;
import com.roomsbooking.Objects.Hello;

@Repository
public class HelloDaoImpl implements HelloDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	private static final String COLLECTION_NAME = "Hello";

	@Override
	public Hello create(Hello hello) {

		if (!mongoTemplate.collectionExists(Hello.class)) {
			mongoTemplate.createCollection(Hello.class);
		}
		// String baseURL = getBaseURL();
		this.mongoTemplate.insert(hello);
		System.out.println("Record is inserted....");

 		return hello;
	}

}
