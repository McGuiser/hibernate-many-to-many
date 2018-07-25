package com.corey.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.corey.hibernate.demo.entity.Course;
import com.corey.hibernate.demo.entity.Instructor;
import com.corey.hibernate.demo.entity.InstructorDetail;
import com.corey.hibernate.demo.entity.Review;
import com.corey.hibernate.demo.entity.Student;

public class DeletePaintingDemo {

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
			
			// Get the painting course from db
			int courseId = 11;
			Course tempCourse = session.get(Course.class, courseId);
			
			// Delete the course
			System.out.println("Deleting course: " + tempCourse);
			
			session.delete(tempCourse);
			
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
