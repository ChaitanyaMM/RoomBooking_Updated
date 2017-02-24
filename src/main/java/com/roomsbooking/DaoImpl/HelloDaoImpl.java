package com.roomsbooking.DaoImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.roomsbooking.Dao.HelloDao;
import com.roomsbooking.Objects.Hello;

@Repository
public class HelloDaoImpl implements HelloDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	private static final String COLLECTION_NAME = "hello";

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

	@Override
	public Hello delete(String id) {
		System.out.println("deleting the account ");
		Hello hello = mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), Hello.class);
		mongoTemplate.remove(hello);
		return hello;

	}

	@Override
	public List<Hello> deleteAll() {
		Query query = new Query(Criteria.where("isDeleted").is(false));
		// mongoTemplate.remove(query,Hello.class,COLLECTION_NAME);

		return mongoTemplate.findAllAndRemove(query, Hello.class, COLLECTION_NAME);

	}

	@Override
	public List<Hello> viewAll() {
		System.out.println("getting into viewAll Dao Impl");
		Query query = new Query(Criteria.where("isDeleted").is(false));
		return mongoTemplate.find(query, Hello.class, COLLECTION_NAME);
	}

	@Override
	public Hello findbyId(String id) {
		System.out.println("finding by id in daoIMpl******");
		Hello hello = mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), Hello.class);
		System.out.println("return before*");
		return hello;

	}

	@Override
	public Hello update(Hello hello) {
		mongoTemplate.save(hello);
		return hello;
	}

}
