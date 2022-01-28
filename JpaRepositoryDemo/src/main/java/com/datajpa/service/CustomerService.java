package com.datajpa.service;

import com.datajpa.dto.CustomerDTO;
import com.datajpa.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
	void insert(CustomerDTO customer);
	void remove(Long phoneNo);
    List<CustomerDTO> getAll();
    void update(Long phoneNo, String address);
    List<CustomerDTO> getSortDecPhoneNum();
    List<CustomerDTO> getPagingElements();
    CustomerDTO get(Long phoneNumber);
    List<CustomerDTO> getCustomerByPlanId(Integer planId) throws CustomerNotFoundException;
    List<CustomerDTO> fetchUsingAddressNamedQuery(String address) throws CustomerNotFoundException;
    List<CustomerDTO> fetchCustomerAgeGT88(int age) throws CustomerNotFoundException;

}