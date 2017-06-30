package com.project.coffee.shop.dao;

import com.project.coffee.shop.dao.exception.ProblemWithDatabaseException;
import com.project.coffee.shop.entity.Coffee;
import com.project.coffee.shop.entity.Order;
import com.project.coffee.shop.entity.OrderElement;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.PersistenceException;
import java.util.List;

public class CoffeeDAO implements DAO {

    private final static String QUERY_SELECT_ALL = "FROM Coffee";

    private final static String QUERY_SELECT_ORDER_ID = "SELECT order_id FROM Configurations";

    private final static String QUERY_INCREMENT_ORDER_ID = "UPDATE Configurations SET order_id=";

    private final static SessionFactory SESSION_HIBERNATE = new Configuration().configure().buildSessionFactory();

    private final static Logger log = Logger.getRootLogger();

    @Override
    public Coffee getCoffeeById(Integer id) throws ProblemWithDatabaseException {
        Transaction tx = null;
        Session session = SESSION_HIBERNATE.openSession();

        try {
            tx = session.beginTransaction();

            Coffee coffee = session.get(Coffee.class, id);

            tx.commit();
            return coffee;
        } catch (PersistenceException e) {
            if (tx != null) {
                tx.rollback();
            }

            log.error("Problem with database : ", e);
            throw new ProblemWithDatabaseException("Problem with database", e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Coffee> getAllCoffee() throws ProblemWithDatabaseException {
        Transaction tx = null;
        Session session = SESSION_HIBERNATE.openSession();
        List<Coffee> coffeeList;

        try {
            tx = session.beginTransaction();

            coffeeList = session.createQuery(QUERY_SELECT_ALL).list();

            tx.commit();
            return coffeeList;
        } catch (PersistenceException e) {
            if (tx != null) {
                tx.rollback();
            }

            log.error("Problem with database : ", e);
            throw new ProblemWithDatabaseException("Problem with database", e);
        } finally {
            session.close();
        }
    }

    @Override
    public void setOrderElement(OrderElement orderElement) throws ProblemWithDatabaseException {
        Transaction tx = null;
        Session session = SESSION_HIBERNATE.openSession();

        try {
            tx = session.beginTransaction();

            session.save(orderElement);

            tx.commit();
        } catch (PersistenceException e) {
            if (tx != null) {
                tx.rollback();
            }

            log.error("Problem with database : ", e);
            throw new ProblemWithDatabaseException("Problem with database", e);
        } finally {
            session.close();
        }
    }

    @Override
    public void setOrder(Order order) throws ProblemWithDatabaseException {
        Transaction tx = null;
        Session session = SESSION_HIBERNATE.openSession();

        try {
            tx = session.beginTransaction();

            session.save(order);

            tx.commit();
        } catch (PersistenceException e) {
            if (tx != null) {
                tx.rollback();
            }

            log.error("Problem with database : ", e);
            throw new ProblemWithDatabaseException("Problem with database", e);
        } finally {
            session.close();
        }
    }

    @Override
    public Integer getNextOrderId() throws ProblemWithDatabaseException {
        Transaction tx = null;
        Session session = SESSION_HIBERNATE.openSession();

        try {
            tx = session.beginTransaction();

            Integer nextId = (Integer) session.createNativeQuery(QUERY_SELECT_ORDER_ID).getSingleResult();
            session.createNativeQuery(QUERY_INCREMENT_ORDER_ID + (nextId + 1)).executeUpdate();

            tx.commit();
            return nextId;
        } catch (PersistenceException e) {
            if (tx != null) {
                tx.rollback();
            }

            log.error("Problem with database : ", e);
            throw new ProblemWithDatabaseException("Problem with database", e);
        } finally {
            session.close();
        }
    }

}
