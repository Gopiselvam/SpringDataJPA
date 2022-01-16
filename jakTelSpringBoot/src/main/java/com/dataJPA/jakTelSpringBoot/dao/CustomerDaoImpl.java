package com.dataJPA.jakTelSpringBoot.dao;

import com.dataJPA.jakTelSpringBoot.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.List;

@Repository("customerRepository")
public class CustomerDaoImpl implements CustomerDAO{

    private EntityManagerFactory entityManagerFactory;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void insert(Customer customer) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
    }

    @Override
    public int remove(Long phoneNo) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        int result = 0;
        try {
            Customer customer = entityManager.find(Customer.class, phoneNo);
            entityManager.remove(customer);
            result = 1;
            entityManager.getTransaction().commit();
        }catch (Exception exception){
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Customer> getAll() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("Select c from Customer c");

        return query.getResultList();
    }

    @Override
    public void update(Long phoneNo, String address) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Customer customer = entityManager.find(Customer.class, phoneNo);
        customer.setAddress(address);
        entityManager.getTransaction().commit();
    }

    @Override
    public Customer get(Long phoneNumber) {

        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Customer customer = entityManager.find(Customer.class, phoneNumber);
        entityManager.getTransaction().commit();

        return customer;
    }
}
