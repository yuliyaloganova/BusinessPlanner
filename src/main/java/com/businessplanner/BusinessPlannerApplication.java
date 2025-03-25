package com.businessplanner;

import com.businessplanner.models.*;
import com.businessplanner.repositories.*;
import com.businessplanner.services.TagService;
import com.businessplanner.services.TaskService;
import com.businessplanner.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;
//import java.time.LocalDate;
//import java.util.Set;

@SpringBootApplication
public class BusinessPlannerApplication {

    @Autowired
    private TagService tagService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

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
    public CommandLineRunner demo() {
        return (args) -> {
            // Создаём тестовых пользователей
            User user1 = new User();
            user1.setName("John Doe");
            user1.setEmail("john.doe@example.com");
            //user1.setPassword(passwordEncoder.encode("password123"));
            user1.setPassword("password123");

            userRepository.save(user1);

            User user2 = new User();
            user2.setName("Jane Smith");
            user2.setEmail("jane.smith@example.com");
            //user2.setPassword(passwordEncoder.encode("password456"));
            user2.setPassword("password456");

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

            Task task3 = new Task();
            task3.setTitle("Task 3");
            task3.setDescription("Description for Task 3");
            task3.setCreator(user1);
            taskRepository.save(task3);

            Task task4 = new Task();
            task4.setTitle("Task 4");
            task4.setDescription("Description for Task 4");
            task4.setCreator(user1);
            taskRepository.save(task4);

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

            TaskTag taskTag4 = new TaskTag();
            taskTag4.setTask(task3);
            taskTag4.setTag(tag3);
            taskTagRepository.save(taskTag4);

            TaskTag taskTag5 = new TaskTag();
            taskTag5.setTask(task4);
            taskTag5.setTag(tag1);
            taskTagRepository.save(taskTag5);

            System.out.println("Таблицы созданы и заполнены тестовыми данными.");

            Long userId = 1L;
            List<Task> tasksForUser1 = taskService.getTasksByCreatorId(userId);
            System.out.println("Задачи для пользователя John Doe:");
            tasksForUser1.forEach(task -> System.out.println(
                "ID: " + task.getId() + 
                ", Название: " + task.getTitle() + 
                ", Описание: " + task.getDescription()
            ));

            userId = 2L;
            List<Task> tasksForUser2 = taskService.getTasksByCreatorId(userId);
            System.out.println("Задачи для пользователя Jane Smith:");
            tasksForUser2.forEach(task -> System.out.println(
                "ID: " + task.getId() + 
                ", Название: " + task.getTitle() + 
                ", Описание: " + task.getDescription()
            ));
            System.out.println("end of 1st test");

            /*String userEmail= "john.doe@example.com";
            Optional<User> users1 = userService.getUserByEmail(userEmail);
            System.out.println("пользователь с почтой john.doe@example.com");
            String email = "john.doe@example.com";
            String tagName = "Urgent";
            List<Task> tasks = taskService.getTasksByUserEmailAndTag(email, tagName);

            System.out.println("Задачи для пользователя " + email + " с тегом '" + tagName + "':");
            tasks.forEach(task -> System.out.println(
                "ID: " + task.getId() + 
                ", Название: " + task.getTitle() + 
                ", Описание: " + task.getDescription()
            ));
            System.out.println("start of 2nd test AAAAAAAAAAAA");*/

            // Получаем задачи для пользователя с email "jane.smith@example.com" и тегом с ID = 3
            String email = "jane.smith@example.com";
            Long tagId = 3L;
            System.out.println("vot tut AAAAAAAAAAAA");
            List<Task> tasks = taskService.getTasksByUserEmailAndTagId(email, tagId);

            System.out.println("Задачи для пользователя " + email + " с тегом (ID: " + tagId + "):");
            tasks.forEach(task -> System.out.println(
                "ID: " + task.getId() + 
                ", Название: " + task.getTitle() + 
                ", Описание: " + task.getDescription()
            ));

            // Получаем задачи для пользователя с email "john.doe@example.com" и тегом с ID = 1
            String email_2 = "john.doe@example.com";
            Long tagId_2 = 1L;
            List<Task> tasks_2 = taskService.getTasksByUserEmailAndTagId(email_2, tagId_2);
            System.out.println("Задачи для пользователя " + email_2 + " с тегом (ID: " + tagId_2 + "):");
            tasks_2.forEach(task -> System.out.println(
                "ID: " + task.getId() + 
                ", Название: " + task.getTitle() + 
                ", Описание: " + task.getDescription()
            ));
            System.out.println("end of 2nd test AAAAAAAAAAAA");


        
            User user3 = new User();
            user3.setName("Bradly Donkey");
            user3.setEmail("donkey@example.com");
            user3.setPassword("password123");
            userRepository.save(user3);
            
            System.out.println("start of 3rd test");
            // Удаляем пользователя по email
            String emailToDelete = "donkey@example.com";
            userService.deleteUserByEmail(emailToDelete);
            System.out.println("Пользователь с email " + emailToDelete + " удалён.");
            System.out.println("end of 3rd test");

        };
    }
}