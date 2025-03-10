package com.businessplanner.services;

import com.businessplanner.models.TaskTag;
import com.businessplanner.repositories.TaskTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTagService {

    @Autowired
    private TaskTagRepository taskTagRepository;

    // Создать связь между задачей и тегом
    public TaskTag createTaskTag(TaskTag taskTag) {
        return taskTagRepository.save(taskTag);
    }

    // Получить все связи
    public List<TaskTag> getAllTaskTags() {
        return taskTagRepository.findAll();
    }

    // Удалить связь по id
    public void deleteTaskTag(Long id) {
        taskTagRepository.deleteById(id);
    }
}