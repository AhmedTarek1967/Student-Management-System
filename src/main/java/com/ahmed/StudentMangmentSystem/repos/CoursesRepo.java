package com.ahmed.StudentMangmentSystem.repos;

import com.ahmed.StudentMangmentSystem.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepo extends JpaRepository<Course,Integer> {
}
