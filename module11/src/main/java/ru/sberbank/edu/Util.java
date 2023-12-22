package ru.sberbank.edu;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.sberbank.edu.entity.User;

public class Util {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().build());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
