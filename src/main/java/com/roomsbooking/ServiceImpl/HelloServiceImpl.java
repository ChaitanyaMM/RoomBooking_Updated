package com.roomsbooking.ServiceImpl;

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

}
