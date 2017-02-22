package com.roomsbooking.DaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roomsbooking.Dao.RoomDao;
import com.roomsbooking.Objects.Room;

@Repository
public class RoomDaoImpl implements RoomDao {
	@Autowired
 private MongoTemplate mongoTemplate;
 
 	private static final String COLLECTION_NAME = "Hello";
 	
//	private HibernateTemplate hibernatetemplate;
//
//	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//		this.hibernatetemplate = hibernateTemplate;
//	}

	@Override
	public String sample() {
		return "Hello ROOM BOOKING!!";

	}

	@Override
	@Transactional(readOnly = false)
	public Room add(Room room) {
		System.out.println("Create new room " + room);
    	//this.mongoTemplate.insert(hello);
		System.out.println("Employee created " + room);
		return room;
	}

}
