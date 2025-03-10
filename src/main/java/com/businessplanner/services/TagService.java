package com.businessplanner.services;

import com.businessplanner.models.Tag;
import com.businessplanner.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    // Создать тег
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    // Получить тег по id
    public Optional<Tag> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    // Удалить тег по id
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    // Получить все теги
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}