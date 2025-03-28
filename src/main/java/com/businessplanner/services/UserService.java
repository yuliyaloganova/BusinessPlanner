package com.businessplanner.services;

import com.businessplanner.models.User;
import com.businessplanner.repositories.UserRepository;

import jakarta.transaction.Transactional;

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

    // Обновить пользователя
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    // Проверить, существует ли пользователь с указанным email
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Удалить пользователя
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Удалить пользователя по email
    @Transactional
    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    // Получить всех пользователей
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}