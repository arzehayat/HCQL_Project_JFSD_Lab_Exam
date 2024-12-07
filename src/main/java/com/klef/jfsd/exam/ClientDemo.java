package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

public class ClientDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Insert records
        Project project1 = new Project();
        project1.setProjectName("E-Commerce Platform");
        project1.setDuration(12);
        project1.setBudget(150000);
        project1.setTeamLead("Alice");

        Project project2 = new Project();
        project2.setProjectName("Inventory Management System");
        project2.setDuration(8);
        project2.setBudget(85000);
        project2.setTeamLead("Bob");

        session.save(project1);
        session.save(project2);

        transaction.commit();

        // Aggregate functions using Criteria API
        Criteria criteria = session.createCriteria(Project.class);

        criteria.setProjection(Projections.rowCount());
        System.out.println("Count: " + criteria.uniqueResult());

        criteria.setProjection(Projections.max("budget"));
        System.out.println("Max Budget: " + criteria.uniqueResult());

        criteria.setProjection(Projections.min("budget"));
        System.out.println("Min Budget: " + criteria.uniqueResult());

        criteria.setProjection(Projections.sum("budget"));
        System.out.println("Total Budget: " + criteria.uniqueResult());

        criteria.setProjection(Projections.avg("budget"));
        System.out.println("Average Budget: " + criteria.uniqueResult());

        session.close();
        sessionFactory.close();
    }
}
