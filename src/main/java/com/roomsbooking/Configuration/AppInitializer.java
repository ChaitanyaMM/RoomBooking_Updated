package com.roomsbooking.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(ApplicationConfig.class);
		ctx.setServletContext(container);

		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));

		System.out.println("servlet>>>>" + servlet);
		System.out.println("");
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/webservices/*");

	}
}
