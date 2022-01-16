package com.jakTel.entity;

public interface CustomerDao {

    public void insert(Customer customer);

    public int remove(Long phoneNumber);


}
