package com.businessplanner.services;

import com.businessplanner.models.Task;
import com.businessplanner.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

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

    // Получить все задачи
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}