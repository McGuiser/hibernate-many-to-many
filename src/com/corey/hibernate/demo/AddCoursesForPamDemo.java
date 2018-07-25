package com.corey.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.corey.hibernate.demo.entity.Course;
import com.corey.hibernate.demo.entity.Instructor;
import com.corey.hibernate.demo.entity.InstructorDetail;
import com.corey.hibernate.demo.entity.Review;
import com.corey.hibernate.demo.entity.Student;

public class AddCoursesForPamDemo {

	public static void main(String[] args) {

		// Create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// Create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// Start a transaction
			session.beginTransaction();
			
			// Get the student pam from database
			int studentId = 1;
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("\nLoaded student: " + tempStudent);
			System.out.println("Course: " + tempStudent.getCourses());
			
			// Create more courses
			Course tempCourse1 = new Course("Painting - How to Paint Flowers");
			Course tempCourse2 = new Course("Taking Phone Calls - Ultimate Guide");
			
			// Add pam to courses
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			
			// Save the courses
			System.out.println("\nSaving the courses ...");
			session.save(tempCourse1);
			session.save(tempCourse2);
			System.out.println("Saved the courses: ");
			
			
			// Commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} finally {
			// Add clean up code
			session.close();
			
			factory.close();
		}

	}

}
