package com.datajpa.service;

import com.datajpa.dao.CustomerRepository;
import com.datajpa.dto.CustomerDTO;
import com.datajpa.entity.Customer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

    Logger logger = Logger.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerRepository customerDAO;

    @Override
    public void insert(CustomerDTO customer) {
        customerDAO.saveAndFlush(CustomerDTO.prepareCustomerEntity(customer));
    }

    @Override
    public void remove(Long phoneNo) {
        customerDAO.deleteById(phoneNo);
    }

    @Override
    public List<CustomerDTO> getAll() {
        List<CustomerDTO> cusList = new ArrayList<>();
        List<Customer> custEntityList = customerDAO.findAll();
        for(Customer customer : custEntityList){
            CustomerDTO customerDTO = Customer.prepareDTO(customer);
            cusList.add(customerDTO);
        }
        return cusList;
    }

    @Override
    public void update(Long phoneNo, String address) {
        Optional<Customer> custOpt = customerDAO.findById(phoneNo);
        Customer customer = null;
        if(custOpt.isPresent()){
            customer = custOpt.get();
            customer.setAddress(address);
            customerDAO.save(customer);
        }else{
            logger.info("No data found with phone number : "+phoneNo);
        }


    }

    @Override
    public CustomerDTO get(Long phoneNumber) {

        Optional<Customer> customerOptional = customerDAO.findById(phoneNumber);

        Customer customer = null;
        if(customerOptional.isPresent()){
            customer = customerOptional.get();
            return Customer.prepareDTO(customer);
        }
        return new CustomerDTO();
    }
}
