package com.datajpa.service;

import com.datajpa.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
	void insert(CustomerDTO customer);
	void remove(Long phoneNo);
    List<CustomerDTO> getAll();
    void update(Long phoneNo, String address);
    List<CustomerDTO> getSortDecPhoneNum();
    List<CustomerDTO> getPagingElements();
    CustomerDTO get(Long phoneNumber);
}