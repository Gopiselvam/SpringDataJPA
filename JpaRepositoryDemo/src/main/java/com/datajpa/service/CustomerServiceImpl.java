package com.datajpa.service;

import com.datajpa.dao.CustomerRepository;
import com.datajpa.dto.CustomerDTO;
import com.datajpa.entity.Customer;
import com.datajpa.exceptions.CustomerNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<CustomerDTO> getCustomerByPlanId(Integer planId) throws CustomerNotFoundException {
         List<Customer> list = customerDAO.findByPlanId(planId)
                 .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found with plan id = "+planId));
         return list.stream().map(Customer::prepareDTO).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getSortDecPhoneNum() {
        List<Customer> list = customerDAO.findAll(Sort.by(Sort.Direction.DESC, "phoneNumber"));
        List<CustomerDTO> dtoList = new ArrayList<>();
        for(Customer customer : list){
            dtoList.add(Customer.prepareDTO(customer));
        }
        return dtoList;
    }

    @Override
    public List<CustomerDTO> getPagingElements() {
        Pageable pageable = PageRequest.of(1, 3);
        Page<Customer> pageList = customerDAO.findAll(pageable);
        List<CustomerDTO> dtoList = new ArrayList<>();
        for(Customer customer : pageList){
            dtoList.add(Customer.prepareDTO(customer));
        }
        return dtoList;
    }
}
