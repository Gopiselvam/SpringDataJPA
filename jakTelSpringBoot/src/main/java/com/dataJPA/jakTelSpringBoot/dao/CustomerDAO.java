package com.dataJPA.jakTelSpringBoot.dao;

import com.dataJPA.jakTelSpringBoot.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    public void insert(Customer customer);
    public int remove(Long phoneNo);
    public List<Customer> getAll();
    public void update(Long phoneNo, String address);
    Customer get(Long phoneNumber);
}
