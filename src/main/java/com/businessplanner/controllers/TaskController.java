package com.businessplanner.controllers;

import com.businessplanner.models.Task;
import com.businessplanner.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Создать задачу
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    // Получить задачу по id
    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // Получить задачи по id создателя
    @GetMapping("/creator/{creatorId}")
    public List<Task> getTasksByCreatorId(@PathVariable Long creatorId) {
        return taskService.getTasksByCreatorId(creatorId);
    }

    // Удалить задачу по id
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    // Получить все задачи
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
}