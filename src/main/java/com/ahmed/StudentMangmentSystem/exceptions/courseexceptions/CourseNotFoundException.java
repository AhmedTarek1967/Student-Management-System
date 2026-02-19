package com.ahmed.StudentMangmentSystem.exceptions.courseexceptions;

import javax.management.RuntimeMBeanException;
import java.util.UUID;

public class CourseNotFoundException extends RuntimeException {
	public CourseNotFoundException(Integer id) {
		super("Course with id " + id + " was not found!");
	}
}
