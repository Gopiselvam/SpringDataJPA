package com.datajpa.service;

import com.datajpa.dao.CreditCustomerRepository;
import com.datajpa.entity.CreditCustomers;
import com.datajpa.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CreditCustomerService {

    @Autowired
    CreditCustomerRepository dao;

    public CreditCustomers findByEmail(String email) throws CustomerNotFoundException {

        return dao.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found With Email = "+email));
    }

    public List<CreditCustomers> findByLastNameLike(String lastName) throws CustomerNotFoundException {
        return dao.findByLastNameLike(lastName)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found With lastName = "+lastName));
    }

    public List<CreditCustomers> findByEmailOrContactNumber(String email, String contactNumber) throws CustomerNotFoundException {
        return dao.findByEmailOrContactNumber(email, contactNumber)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer Not Found With email = "+
                                email + "; Or contact Number = "+ contactNumber));
    }

    public List<CreditCustomers> findByLastNameOrderByFirstNameDesc(String lastName) throws CustomerNotFoundException {
       return  dao.findByLastNameOrderByFirstNameDesc(lastName)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found With lastName = "+lastName));
    }

    public List<CreditCustomers> findByActiveFalse() throws CustomerNotFoundException {
        return dao.findByActiveFalse()
                .orElseThrow(() -> new CustomerNotFoundException("No Inactive customers found"));
    }

    public List<CreditCustomers> findByCreditPointsGreaterThanEqual(int cPoint) throws CustomerNotFoundException {
        return dao.findByCreditPointsGreaterThanEqual(cPoint)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer Not Found With credit point " +
                                "greater than or equal to  = "+cPoint));
    }

    public List<CreditCustomers> findByCreditPointsBetween(int c1point, int c2point) throws CustomerNotFoundException {
        return dao.findByCreditPointsBetween(c1point, c2point)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer Not Found With credit point " +
                                "between "+c1point +" and "+c2point));
    }

    public List<CreditCustomers> findByLastNameAndAddress_CityIn(String lastName, Collection<String> cities) throws CustomerNotFoundException {
        return dao.findByLastNameAndAddress_CityIn(lastName, cities)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer Not Found With last name " + lastName +
                                " or in cities  = "+cities));
    }

    public List<CreditCustomers> lastNameAndAddressCityUsingQueryAnnotation(String lastName, Collection<String> cities) throws CustomerNotFoundException {
        return dao.lastNameAndAddressCityUsingQueryAnnotation(lastName, cities)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer Not Found With last name " + lastName +
                                " or in cities  = "+cities));
    }











}
