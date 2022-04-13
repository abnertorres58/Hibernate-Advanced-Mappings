package com.freudromero.hibernate.demo;

import com.freudromero.hibernate.demo.entity.Course;
import com.freudromero.hibernate.demo.entity.Instructor;
import com.freudromero.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;



public class FetchJoinDemo {

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

            // Option 2: Hibernate query with HQL

            // Get the instructor from DB
            int theId = 2;

            Query<Instructor> query = session.createQuery("select i from Instructor i "
                                                    + "join fetch  i.courses "
                                                    + "where i.id=:theInstructorId",
                                                Instructor.class);

            // Set parameters on query
            query.setParameter("theInstructorId", theId);

            // Execute query and get instructor
            Instructor tempInstructor = query.getSingleResult();


            System.out.println("freudromero: Instructor: " + tempInstructor);



            // Commit the transaction
            session.getTransaction().commit();

            // Close the session
            session.close();

            System.out.println("freudromero: The session is now closed!");

            // Since courses are lazy loaded ... this should fail

            // Option 1: Call getter method while session is still open

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
