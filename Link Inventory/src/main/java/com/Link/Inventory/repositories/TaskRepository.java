package com.Link.Inventory.repositories;

import com.Link.Inventory.controllers.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> { }