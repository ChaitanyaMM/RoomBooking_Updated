package com.roomsbooking.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.roomsbooking.Dao.HelloDao;
import com.roomsbooking.Objects.Hello;
import com.roomsbooking.Service.HelloService;


@Service
@Transactional
public class HelloServiceImpl implements HelloService{
	@Autowired
	private HelloDao hellodao;

	@Override
	public Hello create(Hello hello) {
		
		return hellodao.create(hello);
	}

	@Override
	public Hello delete(String id) {
		
 		return hellodao.delete(id);
	}

	@Override
	public List<Hello> deleteAll( ) {
		System.out.println("deleteAll service IMPl");
 		return hellodao.deleteAll( );
	}

	@Override
	public List<Hello> viewAll() {
 
		return hellodao.viewAll();
	}

	@Override
	public Hello findbyId(String id) {
		// TODO Auto-generated method stub
		return hellodao.findbyId(id);
	}

}
