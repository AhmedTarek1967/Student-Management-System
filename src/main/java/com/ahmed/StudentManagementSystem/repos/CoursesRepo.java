package com.ahmed.StudentManagementSystem.repos;

import com.ahmed.StudentManagementSystem.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepo extends JpaRepository<Course,Integer> {
}
