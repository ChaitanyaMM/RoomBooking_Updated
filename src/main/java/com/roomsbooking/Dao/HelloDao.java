package com.roomsbooking.Dao;

import java.util.List;

import com.roomsbooking.Objects.Hello;

public interface HelloDao {

	public Hello create(Hello hello);

	public Hello delete(String id);

	public List<Hello> deleteAll( );

	public List<Hello> viewAll();

	public Hello findbyId(String id);

	public Hello update(Hello hello);

	 

}
