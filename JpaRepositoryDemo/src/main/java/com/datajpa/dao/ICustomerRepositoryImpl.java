package com.datajpa.dao;

import com.datajpa.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ICustomerRepositoryImpl implements ICustomerRepository{

    private EntityManagerFactory factory;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory factory){
        this.factory = factory;
    }

    @Override
    public List<Customer> searchComplexCustomer(String name, String address, Character gender, Integer age) {

        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);

        Predicate containsName = builder.equal(root.get("name"), name);
        Predicate containsAddress = builder.equal(root.get("address"), address);

        Predicate exp1 = builder.and(containsName, containsAddress);

        Predicate containsGender = builder.equal(root.get("gender"), gender);
        Predicate containsAge = builder.equal(root.get("age"), age);

        Predicate exp2 = builder.or(containsGender, containsAge);

        query.where(builder.or(exp1, exp2));

        return em.createQuery(query.select(root)).getResultList();
    }
}
