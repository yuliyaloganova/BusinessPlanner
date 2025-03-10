package com.businessplanner.controllers;

import com.businessplanner.models.TaskTag;
import com.businessplanner.services.TaskTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-tags")
public class TaskTagController {

    @Autowired
    private TaskTagService taskTagService;

    // Создать связь между задачей и тегом
    @PostMapping
    public TaskTag createTaskTag(@RequestBody TaskTag taskTag) {
        return taskTagService.createTaskTag(taskTag);
    }

    // Получить все связи
    @GetMapping
    public List<TaskTag> getAllTaskTags() {
        return taskTagService.getAllTaskTags();
    }

    // Удалить связь по id
    @DeleteMapping("/{id}")
    public void deleteTaskTag(@PathVariable Long id) {
        taskTagService.deleteTaskTag(id);
    }
}