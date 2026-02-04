package com.ahmed.StudentMangmentSystem.repos;

import aj.org.objectweb.asm.commons.Remapper;
import com.ahmed.StudentMangmentSystem.dtos.StudentResponseDto;
import com.ahmed.StudentMangmentSystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {
	Student findById(int id);
}
