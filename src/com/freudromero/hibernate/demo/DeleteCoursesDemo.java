package com.freudromero.hibernate.demo;

import com.freudromero.hibernate.demo.entity.Course;
import com.freudromero.hibernate.demo.entity.Instructor;
import com.freudromero.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteCoursesDemo {

    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // Create session
        Session session = factory.getCurrentSession();

        try {

            // Start a transaction
            session.beginTransaction();

            // Get a course
            int theId = 10;
            Course tempCourse = session.get(Course.class, theId);

            // Delete a course
            System.out.println("Deleting course: " + tempCourse);

            session.delete(tempCourse);


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
