package com.dataJPA.jakTelSpringBoot.service;

import com.dataJPA.jakTelSpringBoot.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
	void insert(CustomerDTO customer);
	int remove(Long phoneNo);
    List<CustomerDTO> getAll();
    void update(Long phoneNo, String address);

    CustomerDTO get(Long phoneNumber);
}