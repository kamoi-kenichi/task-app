package com.kenichikamoi.taskapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kenichikamoi.taskapp.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}