package com.businessplanner.services;

import com.businessplanner.models.Tag;
import com.businessplanner.models.Task;
import com.businessplanner.models.User;
import com.businessplanner.repositories.TagRepository;
import com.businessplanner.repositories.TaskRepository;
import com.businessplanner.repositories.TaskTagRepository;
import com.businessplanner.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TaskTagRepository taskTagRepository;

    // Создать задачу
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Получить задачу по id
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Получить задачи по id создателя
    public List<Task> getTasksByCreatorId(Long creatorId) {
        return taskRepository.findByCreatorId(creatorId);
    }

    // Удалить задачу по id
    @Transactional  // Указываем, что метод должен выполняться в транзакции
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));  // или кастомное исключение
        task.getTags().clear();  // Удаляем связи с тегами
        taskRepository.delete(task);  // Удаляем саму задачу
    }

    public List<Task> getTasksByUserEmailAndTag(String email, String tagName) {
        // Находим пользователя по email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Находим тег по имени
        Tag tag = tagRepository.findByName(tagName)
                .orElseThrow(() -> new RuntimeException("Tag not found with name: " + tagName));

        List<Task> userTasks = taskRepository.findByCreator(user);
        //List<Task> userTasks = taskRepository.findByCreator(user);
       

        return userTasks.stream()
                .filter(task -> task.getTaskTags().stream()
                        .anyMatch(taskTag -> taskTag.getTag().equals(tag)))
                .collect(Collectors.toList());
    }

    // Обновить задачу
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        return taskRepository.save(task);
    }

    // Получить все задачи
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional
    public List<Task> getTasksByUserEmailAndTagId(String email, Long tagId) {
        // Находим пользователя по email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Находим тег по ID
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag not found with id: " + tagId));

        // Получаем все задачи пользователя
        List<Task> userTasks = taskRepository.findByCreator(user);

        // Фильтруем задачи по тегу
        return userTasks.stream()
                .filter(task -> task.getTaskTags().stream()
                        .anyMatch(taskTag -> taskTag.getTag().equals(tag)))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteTasksByUserEmailAndTagName(String email, String tagName) {
        // Находим пользователя по email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Находим тег по названию
        Tag tag = tagRepository.findByName(tagName)
                .orElseThrow(() -> new RuntimeException("Tag not found with name: " + tagName));

        // Получаем все задачи пользователя
        List<Task> userTasks = taskRepository.findByCreator(user);

        // Удаляем задачи, связанные с указанным тегом
        userTasks.forEach(task -> {
            if (task.getTaskTags().stream().anyMatch(taskTag -> taskTag.getTag().equals(tag))) {
                // Удаляем связи между задачей и тегами
                taskTagRepository.deleteByTaskId(task.getId());
                // Удаляем саму задачу
                taskRepository.delete(task);
            }
        });
    }
}