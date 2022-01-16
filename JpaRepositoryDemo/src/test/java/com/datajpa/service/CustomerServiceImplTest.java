package com.datajpa.service;

import com.datajpa.dto.CustomerDTO;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerServiceImplTest {

    private static List<CustomerDTO> customerList = new ArrayList<>();

    @Autowired
    CustomerService customerService;

    @BeforeAll
    void testInsertPopulateDB(){
        try (FileReader fileReader = new FileReader("src/test/resources/customerInput.csv");
             CSVReader csvReader = new CSVReader(fileReader)) {
            String[] record;
            CustomerDTO customer;
            while ((record = csvReader.readNext()) != null){

                customer = new CustomerDTO();
                customer.setPhoneNumber(Long.parseLong(record[0].trim()));
                customer.setName(record[1]);
                customer.setAge(Integer.parseInt(record[2].trim()));
                customer.setGender(record[3].trim().charAt(0));
                customer.setAddress(record[4]);
                customer.setPlanId(Integer.parseInt(record[5].trim()));
                customerList.add(customer);
                customerService.insert(customer);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }


    @Test
    void getAll() {
        assertTrue(customerService.getAll().containsAll(customerList));
    }

    @Test
    void update() {

        Long phoneNumber = customerList.get(0).getPhoneNumber();
        assertEquals("Missouri", customerService.get(phoneNumber).getAddress());
        customerService.update( phoneNumber, "Washington DC");
        assertEquals("Washington DC", customerService.get(phoneNumber).getAddress());
    }


    @AfterAll
    void testRemoveTruncateTable() {
        for(CustomerDTO dto : customerList){
            customerService.remove(dto.getPhoneNumber());
        }
    }
}