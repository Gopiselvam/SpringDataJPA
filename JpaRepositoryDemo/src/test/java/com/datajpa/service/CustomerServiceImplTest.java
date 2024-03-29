package com.datajpa.service;

import com.datajpa.dto.CustomerDTO;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerServiceImplTest {

    private static final List<CustomerDTO> customerList = new ArrayList<>();

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
        // updating back to Missouri
        customerService.update( phoneNumber, "Missouri");
    }


    @Test
    void testGetSortDecPhoneNum() {
        List<CustomerDTO> sortExpected = sortCustomerInDescOrder(customerList);
        assertEquals(sortExpected, customerService.getSortDecPhoneNum());
    }





    private List<CustomerDTO> sortCustomerInDescOrder(List<CustomerDTO> customerList) {

        List<CustomerDTO> sortedList = customerList.stream().sorted((o1, o2) -> o2.getPhoneNumber()
                .compareTo(o1.getPhoneNumber())).collect(Collectors.toList());
        return sortedList;
    }


    @Test
    void getPagingElements() {
        List<CustomerDTO> sortedByPhoneNo = getSortedListWithPhoneNUmber(customerList);
//        List<CustomerDTO> subList = sortedByPhoneNo.subList(3,5);
        PagedListHolder<CustomerDTO> pagedListHolder = new PagedListHolder<>(sortedByPhoneNo);
        pagedListHolder.setPageSize(3);
        pagedListHolder.setPage(1);
        assertEquals(pagedListHolder.getPageList(), customerService.getPagingElements());
    }


    private List<CustomerDTO> getSortedListWithPhoneNUmber(List<CustomerDTO> list) {
        List<CustomerDTO> sortedList = list.stream()
                .sorted(Comparator.comparing(CustomerDTO::getPhoneNumber))
                .collect(Collectors.toList());
        return sortedList;
    }

    @SneakyThrows
    @Test
    void getCustomerByPlanId() {
        List<CustomerDTO> expected = customerList.stream().filter(x -> x.getPlanId() == 1).collect(Collectors.toList());
        List<CustomerDTO> actual = customerService.getCustomerByPlanId(1);
        assertTrue(expected.containsAll(actual));
    }

    @SneakyThrows
    @Test
    void fetchUsingAddressNamedQuery() {
        List<CustomerDTO> expected = customerList.stream().filter(x -> x.getAddress().equals("New York")).collect(Collectors.toList());
        List<CustomerDTO> actual = customerService.fetchUsingAddressNamedQuery("New York");
        assertTrue(expected.containsAll(actual));
    }

    @SneakyThrows
    @Test
    void fetchCustomerAgeGT88() {
        List<CustomerDTO> expected = customerList.stream().filter(x -> x.getAge() > 80).collect(Collectors.toList());
        List<CustomerDTO> actual = customerService.fetchCustomerAgeGT88(80);
        assertTrue(expected.containsAll(actual));
    }

    @SneakyThrows
    @Test
    void searchComplexCustomer(){
        Predicate<CustomerDTO> p1  = (c) -> c.getName().equals("Donald Trump");
        Predicate<CustomerDTO> p2  = (c) -> c.getAddress().equals("New York");
        Predicate<CustomerDTO> p3  = (c) -> c.getGender().equals('F');
        Predicate<CustomerDTO> p4  = (c) -> c.getAge().equals(1);
        Predicate<CustomerDTO> exp1 = p1.and(p2);
        Predicate<CustomerDTO> exp2 = p3.or(p4);
        List<CustomerDTO> expected = customerList.stream().filter(exp1.or(exp2)).collect(Collectors.toList());
        List<CustomerDTO> actual = customerService.searchComplexCustomer("Donald Trump", "New York", 'F', 1);

        assertEquals(expected, actual);
    }

    @AfterAll
    void testRemoveTruncateTable() {
        for(CustomerDTO dto : customerList){
            customerService.remove(dto.getPhoneNumber());
        }
    }



}