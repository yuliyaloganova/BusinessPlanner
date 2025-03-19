package com.businessplanner.repositories;

import com.businessplanner.models.Tag;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(String name);
    Optional<Tag> findById(Long id);
}