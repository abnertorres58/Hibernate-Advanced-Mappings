package com.freudromero.hibernate.demo;

import com.freudromero.hibernate.demo.entity.Course;
import com.freudromero.hibernate.demo.entity.Instructor;
import com.freudromero.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class EagerLazyDemo {

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

            // Get the instructor from DB
            int theId = 2;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("freudromero: Instructor: " + tempInstructor);

            // Get courses for the instructor
            System.out.println("freudromero: Instructor: " + tempInstructor.getFirstName() + " " + tempInstructor.getLastName() +" Courses: " + tempInstructor.getCourses());


            // Commit the transaction
            session.getTransaction().commit();

            // Close the session
            session.close();

            // Since courses are lazy loaded ... this should fail

            // Get courses for the instructor
            System.out.println("freudromero: Instructor: " + tempInstructor.getFirstName() + " " + tempInstructor.getLastName() +" Courses: " + tempInstructor.getCourses());


            System.out.println("freudromero: Done!");
        }
        finally {
            // Add clean up code
            session.close();
            factory.close();
        }

    }
}
