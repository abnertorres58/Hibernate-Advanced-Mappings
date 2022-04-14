package com.freudromero.hibernate.demo;

import com.freudromero.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLOutput;


public class CreateCourseAndStudentsDemo {

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

            // Create a course
            Course tempCourse = new Course("Pacman - How to Score One Million Points");

            // Save the course
            System.out.println("\nSaving the course...");
            session.save(tempCourse);
            System.out.println("Saved the course: " + tempCourse);

            // Create the students
            Student tempStudent1 = new Student("John", "Doe", "john@mail.com");
            Student tempStudent2 = new Student("Mary", "Public", "mary@mail.com");

            // Add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            // Save the students
            System.out.println("\nSaving the students");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved students: " + tempCourse.getStudents());

            // Commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            // Add clean up code
            session.close();
            factory.close();
        }

    }
}
