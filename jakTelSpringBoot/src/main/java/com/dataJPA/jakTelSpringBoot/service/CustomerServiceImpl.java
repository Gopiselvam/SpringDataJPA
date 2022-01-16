package com.dataJPA.jakTelSpringBoot.service;

import com.dataJPA.jakTelSpringBoot.dao.CustomerDAO;
import com.dataJPA.jakTelSpringBoot.dto.CustomerDTO;
import com.dataJPA.jakTelSpringBoot.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public void insert(CustomerDTO customer) {
        customerDAO.insert(CustomerDTO.prepareCustomerEntity(customer));
    }

    @Override
    public int remove(Long phoneNo) {

        return customerDAO.remove(phoneNo);
    }

    @Override
    public List<CustomerDTO> getAll() {
        List<CustomerDTO> cusList = new ArrayList<>();
        List<Customer> custEntityList = customerDAO.getAll();
        for(Customer customer : custEntityList){
            CustomerDTO customerDTO = Customer.prepareDTO(customer);
            cusList.add(customerDTO);
        }
        return cusList;
    }

    @Override
    public void update(Long phoneNo, String address) {
        customerDAO.update(phoneNo, address);
    }

    @Override
    public CustomerDTO get(Long phoneNumber) {
        Customer customer = customerDAO.get(phoneNumber);
        CustomerDTO customerDTO = Customer.prepareDTO(customer);
        return customerDTO;
    }
}
