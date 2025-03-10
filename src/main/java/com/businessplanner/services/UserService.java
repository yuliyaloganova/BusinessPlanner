package com.businessplanner.services;

import com.businessplanner.models.User;
import com.businessplanner.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    // Создать пользователя
    public User createUser(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }
    

    // Получить пользователя по id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Получить пользователя по emai
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Проверить, существует ли пользователь с указанным email
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Удалить пользователя по email
    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    // Получить всех пользователей
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}