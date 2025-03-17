package com.businessplanner.repositories;

import com.businessplanner.models.Task;
import com.businessplanner.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCreatorId(Long creatorId);
    List<Task> findByEmail(String email);
    //List<Task> findByCreator(String email);
    List<Task> findByCreator(User creator);

}