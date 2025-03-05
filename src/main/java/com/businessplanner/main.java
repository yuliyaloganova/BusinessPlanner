package com.businessplanner;

import com.businessplanner.models.User;
import com.businessplanner.repositories.UserRepository;
import com.businessplanner.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class main{

    @Autowired
    private UserService userService;

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(main.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            // Создаём пользователя
            User user = new User();
            user.setName("John Doe");
            user.setEmail("john.doe@example.com");
            user.setPassword("password123"); // Пароль будет хеширован

            // Сохраняем пользователя в базу данных
            userService.createUser(user);
            System.out.println("Пользователь создан: " + user);

            // Проверяем пароль
            String rawPassword = "password123"; // Введённый пароль
            String hashedPassword = user.getPassword(); // Хешированный пароль из базы данных
            /*
            boolean isMatch = passwordEncoder.matches(rawPassword, hashedPassword);
            System.out.println("Пароль совпадает: " + isMatch);

            // Проверяем неверный пароль
            String wrongPassword = "wrongpassword";
            boolean isWrongMatch = passwordEncoder.matches(wrongPassword, hashedPassword);
            System.out.println("Неверный пароль совпадает: " + isWrongMatch); */
        };
    }
}