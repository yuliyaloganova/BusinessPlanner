package com.businessplanner;

import com.businessplanner.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.time.LocalDateTime;

public class main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Создание пользователя
            User user = new User();
            user.setName("John Doe");
            user.setEmail("john@example.com");
            user.setPassword("password123");
            session.persist(user);

            // Создание задачи
            Task task = new Task();
            task.setTitle("First Task");
            task.setDescription("This is a test task");
            task.setDueDate(LocalDateTime.now().plusDays(7));
            task.setStatus(TaskStatus.PENDING);
            task.setCreator(user);
            session.persist(task);

            session.getTransaction().commit();
        }
    }
}
