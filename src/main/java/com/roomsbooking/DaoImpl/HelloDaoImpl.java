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

	@Override
	public Hello delete(String id) {
		System.out.println("deleting the account ");
		Hello hello = mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), Hello.class);
		mongoTemplate.remove(hello);
		return hello;

	}

	@Override
	public List<Hello> deleteAll() {
		// System.out.println("deleteAll DaoIMpl");
		// Query query = new Query();
		// query.addCriteria(Criteria.where("isDeleted").is(false));
		// List<Hello> hello =mongoTemplate.find(query, Hello.class);
		// mongoTemplate.remove(hello);
		// System.out.println("done"+hello);
		return null;

	}

	@Override
	public List<Hello> viewAll() {
		System.out.println("getting into viewAll Dao Impl");
		Query query = new Query();
		query.addCriteria(Criteria.where("isDeleted").is(false));
		return mongoTemplate.find(query, Hello.class,COLLECTION_NAME);
		// List<Hello> fetched
		// =mongoTemplate.find(Query.query(Criteria.where("isDeleted").is(false)),
		// Hello.class,COLLECTION_NAME);
		// return fetched;
	}

	@Override
	public Hello findbyId(String id) {
		System.out.println("finding by id in daoIMpl******");
		Query query = new Query(Criteria.where("_id").is(id).andOperator(Criteria.where("_isDeleted").is(false)));
		System.out.println("return before*");
		return this.mongoTemplate.findOne(query, Hello.class, COLLECTION_NAME);

	}

}
