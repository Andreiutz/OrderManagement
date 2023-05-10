package com.order_management.ordermanagement.repository.hibernate;

import com.order_management.ordermanagement.model.Agent;
import com.order_management.ordermanagement.repository.AgentsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class AgentsDbOrmRepository implements AgentsRepository {

    private static SessionFactory sessionFactory;

    public AgentsDbOrmRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e)
        {
            System.out.println("Exceptie: " + e.getMessage());
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Override
    public Agent findById(String s) {
        Agent agent = new Agent();
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                agent = session.createQuery("from Agent where id = ?1", Agent.class)
                        .setParameter(1, s)
                        .setMaxResults(1)
                        .uniqueResult();
                tx.commit();
                return agent;
            } catch (RuntimeException exception) {
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
        return null;
    }

    @Override
    public List<Agent> findAll() {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Agent> agents =
                        session.createQuery("from Agent as m order by m.id asc", Agent.class)
                                .list();
                tx.commit();
                return agents;
            } catch (RuntimeException exception) {
                System.err.println("Error at select all agents " + exception);
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
        return new ArrayList<>();
    }

    @Override
    public boolean save(Agent entity) {
        return false;
    }

    @Override
    public Agent delete(String s) {
        return null;
    }

    @Override
    public boolean update(Agent entity, String s) {
        return false;
    }
}
