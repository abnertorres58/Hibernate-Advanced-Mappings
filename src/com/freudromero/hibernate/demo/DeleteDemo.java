package com.freudromero.hibernate.demo;

import com.freudromero.hibernate.demo.entity.Instructor;
import com.freudromero.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteDemo {

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



            // Start a transaction
            session.beginTransaction();

            // Get the instructor by primary key / id
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("Found instructor: " + tempInstructor);

            // Delete the instructor
            if(tempInstructor != null) {

                System.out.println("Deleting: " + tempInstructor);

                // Note: this will also delete associated "details" object
                // because of CascadeType.All
                session.delete(tempInstructor);

            }



            // Commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }

    }
}
