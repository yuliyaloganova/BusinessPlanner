package com.businessplanner.controllers;

import com.businessplanner.models.Tag;
import com.businessplanner.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    // Создать тег
    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    // Получить тег по id
    @GetMapping("/{id}")
    public Optional<Tag> getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }

    // Удалить тег по id
    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
    }

    // Получить все теги
    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }
}