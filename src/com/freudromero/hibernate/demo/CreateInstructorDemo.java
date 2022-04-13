package com.freudromero.hibernate.demo;

import com.freudromero.hibernate.demo.entity.Course;
import com.freudromero.hibernate.demo.entity.Instructor;
import com.freudromero.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateInstructorDemo {

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

            // Create the Objects
            Instructor tempInstructor = new Instructor("Susan", "Public", "susan.public@mail.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com", "Video Games");

            // Associate the Objects together
            tempInstructor.setInstructorDetail(tempInstructorDetail);


            // Start a transaction
            session.beginTransaction();

            // Save the instructor
            // Note: this will also save the details object
            // Because of CascadeType.All
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);


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
