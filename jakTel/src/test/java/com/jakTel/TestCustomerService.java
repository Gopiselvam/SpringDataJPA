package com.jakTel;

import com.jakTel.dto.CustomerDTO;
import com.jakTel.services.CustomerService;
import com.jakTel.services.CustomerServiceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCustomerService {

    private static CustomerService customerService;
    private static CustomerDTO customerDTO;

    @BeforeClass
    public static void initBeans(){
        customerDTO = new CustomerDTO();
        customerService = new CustomerServiceImpl();
    }

    @Test
    public void testInsertCustomer(){


        customerDTO.setPhoneNumber(9632587410L);
        customerDTO.setName("James Bond");
        customerDTO.setAge(55);
        customerDTO.setGender('M');
        customerDTO.setAddress("Nabraska");
        customerDTO.setPlanId(1001);

        customerService.insert(customerDTO);

    }

    @Test
    public void testRemoveCustomer(){
        customerService.remove(9632587410L);
    }

    @AfterClass
    public static void destroyBeans(){
        customerDTO = null;
        customerService = null;
    }
}
