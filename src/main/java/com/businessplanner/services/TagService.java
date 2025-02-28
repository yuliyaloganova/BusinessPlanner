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

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Optional<Tag> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}