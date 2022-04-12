package com.freudromero.hibernate.demo;

import com.freudromero.hibernate.demo.entity.Instructor;
import com.freudromero.hibernate.demo.entity.InstructorDetail;
import com.freudromero.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class CreateDemo {

    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // Create session
        Session session = factory.getCurrentSession();

        try {

            // Create the Objects
//            Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@mail.com");
//            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.coding.com/youtube", "passion for coding");

            Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@mail.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "Play Guitar");

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
            factory.close();
        }

    }
}
