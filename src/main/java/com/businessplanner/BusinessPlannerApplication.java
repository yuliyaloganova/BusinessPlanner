package com.businessplanner;

import com.businessplanner.models.*;
import com.businessplanner.repositories.*;
import com.businessplanner.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class BusinessPlannerApplication {

   // @Autowired
   // private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TaskTagRepository taskTagRepository;

    public static void main(String[] args) {
        SpringApplication.run(BusinessPlannerApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(TaskRepository taskRepository) {
        return (args) -> {
            // Создаём пользователя
            /*User user = new User();
            user.setName("John Doer");
            user.setEmail("john.doe@example1.com");
            user.setPassword("password124"); // Пароль будет хеширован

            // Сохраняем пользователя в базу данных
            userService.createUser(user);
            System.out.println("Пользователь создан: " + user);*/

            // Создаём задачи
            Task task1 = new Task();
            task1.setName("Task 1");
            System.out.println("task1 создан: " + task1);
            //taskRepository.save(task1);
            taskService.createTask(task1);
            

            Task task2 = new Task();
            task2.setName("Task 2");
            taskRepository.save(task2);

            // Создаём теги
            Tag tag1 = new Tag();
            tag1.setName("Tag 1");
            tagRepository.save(tag1);

            Tag tag2 = new Tag();
            tag2.setName("Tag 2");
            tagRepository.save(tag2);

            Tag tag3 = new Tag();
            tag3.setName("Tag 3");
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

            // Выводим информацию о созданных связях
            System.out.println("Созданы связи между задачами и тегами:");
            taskTagRepository.findAll().forEach(tt -> {
                System.out.println("Task: " + tt.getTask().getName() + ", Tag: " + tt.getTag().getName());
            });
        };
    }
}