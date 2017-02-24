package com.roomsbooking.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roomsbooking.Objects.Hello;
import com.roomsbooking.Service.HelloService;
    @RestController
	@RequestMapping("hello")
	public class HelloController{
		private static final Logger logger = Logger.getLogger(HelloController.class);

		public static final String APPLICATION_JSON = "application/json";
	 	 @Autowired(required=true)
	 	 private HelloService helloservice;
	 	 
	 	 
	@RequestMapping(value = "/hellosample", method = RequestMethod.GET)
	public String printHello() {
		System.out.println("sample services is Calling");
		 
		 return "Hai HelloController is Working !!!";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> delete(@RequestParam String id) {
		
		Hello delete =helloservice.delete(id);
		Map<String,Object> res = new HashMap<String,Object>();
		                   res.put("deleted Successfully",delete.name);
		                   res.put("statusCode", HttpStatus.OK.value());
         System.out.println("deleted Successfully...."); 
		 
		 return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);

	}
	@RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteAll() {
		System.out.println("getting into deleteAll service");
		
		List<Hello> delete =helloservice.deleteAll( );
		Map<String,Object> res = new HashMap<String,Object>();
		                   res.put("All files have been deleted..!",delete);
		                   res.put("statusCode", HttpStatus.OK.value());
         System.out.println("deleted Successfully...."); 
		 
		 return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);

	}
	@RequestMapping(value = "/view-All", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> viewAll() {
		System.out.println("getting into deleteAll service");
		
		List<Hello> feteched =helloservice.viewAll( );
		System.out.println(feteched);
		Map<String,Object> res = new HashMap<String,Object>();
		                   res.put("Successful.!!",feteched);
		                   res.put("statusCode", HttpStatus.OK.value());
      
		 
		 return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);

	}
	@RequestMapping(value = "/findbyId/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findbyId(@PathVariable("id") String id) {
		System.out.println("getting into findbyid controller");
		 Hello feteched =helloservice.findbyId(id);
		  
		System.out.println(feteched);
		Map<String,Object> res = new HashMap<String,Object>();
		                   res.put("Successful.!!",feteched);
		                   res.put("statusCode", HttpStatus.OK.value());
      
		 
		 return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> add(@RequestBody String data) {
		System.out.println("gettign in priar ");
		Hello hello = null;
		System.out.println("gettign in ");

		try {
			hello = new ObjectMapper().readValue(data, Hello.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		hello = helloservice.create(hello);

		System.out.println("sample services is Calling");
		System.out.println("Test Method is getting Called.");
		Map<String, Object> res = new HashMap<String, Object>();
		Object message = null;
		res.put("message", message);
		res.put("statusCode", HttpStatus.OK.value());
		res.put("successful", true);
		res.put("Successful",hello);
		logger.info("Succesfully Added Account"+hello);

		return new ResponseEntity<Map<String, Object>>(res, HttpStatus.CREATED);
	}
}
