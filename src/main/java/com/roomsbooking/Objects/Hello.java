package com.roomsbooking.Objects;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

@Entity
public class Hello {
//implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	  public String name;
	  public String id;
	  public String email;
 


}
