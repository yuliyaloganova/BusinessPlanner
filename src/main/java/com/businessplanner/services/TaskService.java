package com.businessplanner.services;

import com.businessplanner.models.Tag;
import com.businessplanner.models.Task;
import com.businessplanner.models.User;
import com.businessplanner.repositories.TagRepository;
import com.businessplanner.repositories.TaskRepository;
import com.businessplanner.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByUserEmailAndTag(String email, String tagName) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        Tag tag = tagRepository.findByName(tagName)
                .orElseThrow(() -> new RuntimeException("Tag not found with id: " + tagName));

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
}