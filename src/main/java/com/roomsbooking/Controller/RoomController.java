package com.roomsbooking.Controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roomsbooking.Objects.Room;
import com.roomsbooking.Service.RoomService;

@RestController
@RequestMapping("room")
public class RoomController{
	private static final Logger logger = Logger.getLogger(RoomController.class);

	public static final String APPLICATION_JSON = "application/json";
 	 @Autowired
 	 private RoomService roomservice;
 
   @RequestMapping(value ="/sample",method = RequestMethod.GET)
   public String printHello() {
	     System.out.println("sample services is Calling");
	     logger.info("room Controller Sample service..");
       return "Hey Buddy !! Docker is Up and *sample services is Calling";
   }
   
//   @RequestMapping(path ="room/sample1",method = RequestMethod.GET)
//   public String printHello1(ModelMap model) {
//      model.addAttribute("message", "Hello Spring MVC Framework!");
//
//      return "Hai THis it Room ";
//   }
	 
   @RequestMapping(value ="/sample3",method = RequestMethod.GET)
   public ResponseEntity<Map<String, Object>> SampleService(ModelMap model) {
	   System.out.println("hiiiiiiiiii");
      model.addAttribute("message", "Hello Spring MVC Framework!");
      Map<String,Object> responce = new HashMap<String,Object>();
      String sample= roomservice.sample();
      responce.put("HAI", sample);

      return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.OK);
   }
   @RequestMapping (value ="/add",method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>>  Create(@RequestBody String data){
		System.out.println("data "+data);

		System.out.println("Add Service is Calling ");
		  Room room=null;
		      try {
		    	  room = new ObjectMapper().readValue(data, Room.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		      System.out.println("room is Checking "+room);
		      System.out.println("Hai Creating room In mongo");
		      Room object = roomservice.add(room);
		 System.out.println("userservice...................<><"+ roomservice);

		 System.out.println("object<><"+ object);
		 Map<String,Object> response = new HashMap<String,Object>();
		 response.put("User Added.", room);

		 
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	 	
	}
 
 
	

}