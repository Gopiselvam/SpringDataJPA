package com.jakTel.services;

import com.jakTel.dto.CustomerDTO;
import com.jakTel.entity.CustomerDao;
import com.jakTel.entity.CustomerDaoImplJDBC;

public class CustomerServiceImpl implements CustomerService{
    CustomerDao dao =new CustomerDaoImplJDBC();

    @Override
    public void insert(CustomerDTO customerDTO) {
        dao.insert(CustomerDTO.prepareCustomerEntity(customerDTO));
    }

    @Override
    public int remove(Long phoneNumber) {

        return dao.remove(phoneNumber);
    }
}
