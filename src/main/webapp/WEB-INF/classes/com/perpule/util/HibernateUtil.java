package com.perpule.util;


import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.perpule.bo.Customer;

public class HibernateUtil {
  private static StandardServiceRegistry registry;
  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        StandardServiceRegistryBuilder registryBuilder = 
            new StandardServiceRegistryBuilder();

        Map<String, String> settings = new HashMap<>();
        settings.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        settings.put("hibernate.connection.url", "jdbc:mysql://35.196.69.15:3306/CRM");
        settings.put("hibernate.connection.username", "sanjay");
        settings.put("hibernate.connection.password", "sanjay");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.hbm2ddl.auto", "update");

        registryBuilder.applySettings(settings);

        registry = registryBuilder.build();

        MetadataSources sources = new MetadataSources(registry)
            .addAnnotatedClass(Customer.class);

        Metadata metadata = sources.getMetadataBuilder().build();

        sessionFactory = metadata.getSessionFactoryBuilder().build();
      } catch (Exception e) {
        System.out.println("SessionFactory creation failed");
        if (registry != null) {
          StandardServiceRegistryBuilder.destroy(registry);
        }
      }
    }
    return sessionFactory;
  }

  public static void shutdown() {
    if (registry != null) {
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }
}