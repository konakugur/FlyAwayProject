package com.konak.db;

import org.hibernate.SessionFactory;


import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.konak.entity.AdminUser;
import com.konak.entity.Flight;

public class HibernateUtil {
	
	public static SessionFactory getSessionFactory(String driver, String url, String username, String password) {
		SessionFactory factory = null;
		Configuration configuration = new Configuration();	
		
		System.out.println("conf util : " + configuration );
		
		configuration.setProperty("hibernate.connection.driver_class", driver);
        configuration.setProperty("hibernate.connection.url", url);
        configuration.setProperty("hibernate.connection.username", username);
        configuration.setProperty("hibernate.connection.password", password);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        configuration.setProperty(Environment.HBM2DDL_AUTO, "create-drop");
        configuration.addAnnotatedClass(Flight.class);
        configuration.addAnnotatedClass(AdminUser.class);
        
        
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        factory = configuration.buildSessionFactory(builder.build());
        
        return factory;
	}

}
