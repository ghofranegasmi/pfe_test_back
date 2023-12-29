package com.project.back_app.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.back_app.model.Course;

public interface CourceRepository extends JpaRepository<Course, Long> {}