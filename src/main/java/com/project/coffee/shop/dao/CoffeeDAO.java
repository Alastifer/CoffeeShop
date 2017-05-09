package com.project.coffee.shop.dao;

import com.project.coffee.shop.dao.exception.DAOException;
import com.project.coffee.shop.dao.exception.NoSuchEntityException;
import com.project.coffee.shop.entity.Coffee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CoffeeDAO implements DAO {

    private final static String QUERY_SELECT_ALL = "FROM Coffee";

    private final static SessionFactory sessionHibernate = new Configuration().configure().buildSessionFactory();

    @Override
    public Coffee getCoffeeById(Integer id) throws DAOException, NoSuchEntityException {
        try (Session session = sessionHibernate.openSession()) {
            Coffee coffee = session.get(Coffee.class, id);

            if (coffee == null) {
                throw new NoSuchEntityException("No coffee with id = " + id);
            }

            return coffee;
        } catch (HibernateException e) {
            throw new DAOException("Problem with database", e);
        }
    }

    @Override
    public List<Coffee> getAllCoffee() throws DAOException {
        try (Session session = sessionHibernate.openSession()) {
            return session.createQuery(QUERY_SELECT_ALL).list();
        } catch (HibernateException e) {
            throw new DAOException("Problem with database", e);
        }
    }

}
