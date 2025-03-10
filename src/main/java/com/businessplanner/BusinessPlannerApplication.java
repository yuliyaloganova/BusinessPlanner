package com.businessplanner;

import com.businessplanner.models.*;
import com.businessplanner.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

//import java.time.LocalDate;
//import java.util.Set;

@SpringBootApplication
public class BusinessPlannerApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TaskTagRepository taskTagRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BusinessPlannerApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            // Создаём тестовых пользователей
            User user1 = new User();
            user1.setName("John Doe");
            user1.setEmail("john.doe@example.com");
            user1.setPassword(passwordEncoder.encode("password123"));
            userRepository.save(user1);

            User user2 = new User();
            user2.setName("Jane Smith");
            user2.setEmail("jane.smith@example.com");
            user2.setPassword(passwordEncoder.encode("password456"));
            userRepository.save(user2);

            // Создаём тестовые задачи
            Task task1 = new Task();
            task1.setTitle("Task 1");
            task1.setDescription("Description for Task 1");
            //task1.setDueDate(LocalDate.of(2023, 12, 1));
            //task1.setStatus("IN_PROGRESS");
            task1.setCreator(user1);
            taskRepository.save(task1);

            Task task2 = new Task();
            task2.setTitle("Task 2");
            task2.setDescription("Description for Task 2");
            //task2.setDueDate(LocalDate.of(2023, 12, 15));
            //task2.setStatus("TODO");
            task2.setCreator(user2);
            taskRepository.save(task2);

            // Создаём тестовые теги
            Tag tag1 = new Tag();
            tag1.setName("Urgent");
            tagRepository.save(tag1);

            Tag tag2 = new Tag();
            tag2.setName("Important");
            tagRepository.save(tag2);

            Tag tag3 = new Tag();
            tag3.setName("Low Priority");
            tagRepository.save(tag3);

            // Создаём связи между задачами и тегами
            TaskTag taskTag1 = new TaskTag();
            taskTag1.setTask(task1);
            taskTag1.setTag(tag1);
            taskTagRepository.save(taskTag1);

            TaskTag taskTag2 = new TaskTag();
            taskTag2.setTask(task1);
            taskTag2.setTag(tag2);
            taskTagRepository.save(taskTag2);

            TaskTag taskTag3 = new TaskTag();
            taskTag3.setTask(task2);
            taskTag3.setTag(tag3);
            taskTagRepository.save(taskTag3);

            System.out.println("Таблицы созданы и заполнены тестовыми данными.");
        };
    }
}