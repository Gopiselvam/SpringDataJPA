package com.jakTel.services;

import com.jakTel.dto.CustomerDTO;

public interface CustomerService {

    public void insert(CustomerDTO customerDTO);

    public int remove(Long phoneNumber);

}
