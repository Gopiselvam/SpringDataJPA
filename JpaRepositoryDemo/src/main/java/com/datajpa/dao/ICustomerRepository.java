package com.datajpa.dao;

import com.datajpa.entity.Customer;

import java.util.List;

public interface ICustomerRepository {

    public List<Customer> searchComplexCustomer(String name, String address, Character gender, Integer age);
}
