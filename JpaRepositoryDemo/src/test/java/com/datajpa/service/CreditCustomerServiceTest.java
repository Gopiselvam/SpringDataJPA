package com.datajpa.service;

import com.datajpa.entity.Address;
import com.datajpa.entity.CreditCustomers;
import com.datajpa.exceptions.CustomerNotFoundException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"/import_credit_customers.sql"})
@SqlGroup({
        @Sql(scripts = {"/import_credit_customers.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = {"/truncate_credit_customer.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class CreditCustomerServiceTest {

    @Autowired
    CreditCustomerService service;

    List<CreditCustomers> list;

    @BeforeAll
    void initiateList(){
        list = Arrays.asList(
                new CreditCustomers(1001, true, 56, "Anoop", "Gupta", "9874563210", "anoop@gmail.com", new Address(101, "Vadodara", "391421")),
                new CreditCustomers(1002, true, 45, "Shreya", "Gupta", "7896541230", "shreya@gmail.com",new Address(102, "Delhi", "110006")),
                new CreditCustomers(1003, true, 72, "Senthil", "Kumar", "9632587410", "senthil@gmail.com",new Address(103, "Theni", "625513")),
                new CreditCustomers(1004, false, 22, "Senthil", "Singh", "7412589630", "sensing@gmail.com",new Address(104, "Amritsar", "143502")),
                new CreditCustomers(1005, true, 35, "Kapil", "Singh", "7534216980", "kapsi@gmail.com",new Address(105, "Delhi", "110006"))
        );
    }

    @SneakyThrows
    @Test
    void findByEmailTest(){
        Address expectedAddress = new Address(104, "Amritsar", "143502");
        CreditCustomers expected = new CreditCustomers(1004, false, 22, "Senthil", "Singh", "7412589630", "sensing@gmail.com", expectedAddress);
        assertEquals(expected.getFirstName(), service.findByEmail("sensing@gmail.com").getFirstName());
    }

    @Test
    void findByEmailTestException(){
        assertThrows(CustomerNotFoundException.class, () ->  service.findByEmail("unknown email"));
    }






    @SneakyThrows
    @Test
    void findByLastNameLike() {
        List<CreditCustomers> expected = list.stream().filter(s -> s.getLastName().contains("ing")).collect(Collectors.toList()); ;
        List<CreditCustomers> actual = service.findByLastNameLike("%ing%");

        assertEquals(expected, actual);
    }

    @SneakyThrows
    @Test
    void findByEmailOrContactNumber() {

        List<CreditCustomers> expected = list.stream()
                .filter(s -> s.getContactNumber().equals("7896541230") || s.getEmail().equals("senthil@gmail.com"))
                .collect(Collectors.toList());
        List<CreditCustomers> actual = service.findByEmailOrContactNumber("senthil@gmail.com", "7896541230");

        assertEquals(expected, actual);

    }

    @SneakyThrows
    @Test
    void findByLastNameOrderByFirstNameDesc() {

        List<CreditCustomers> expected = list.stream().filter(c -> c.getLastName().equals("Gupta"))
                .sorted(Comparator.comparing(CreditCustomers::getFirstName).reversed())
                .collect(Collectors.toList());
        List<CreditCustomers> actual = service.findByLastNameOrderByFirstNameDesc("Gupta");
        assertEquals(expected, actual);
    }

    @SneakyThrows
    @Test
    void findByActiveFalse() {
        List<CreditCustomers> expected = list.stream().filter(c -> !c.isActive())
                .collect(Collectors.toList());
        List<CreditCustomers> actual = service.findByActiveFalse();
        assertEquals(expected, actual);
    }

    @SneakyThrows
    @Test
    void findByCreditPointsGreaterThanEqual() {
        List<CreditCustomers> expected = list.stream().filter(c -> c.getCreditPoints() >= 56 )
                .collect(Collectors.toList());
        List<CreditCustomers> actual = service.findByCreditPointsGreaterThanEqual(56);
        assertEquals(expected, actual);
    }

    @SneakyThrows
    @Test
    void findByCreditPointsBetween() {
        List<CreditCustomers> expected = list.stream().filter(c -> {return c.getCreditPoints() >= 45 && c.getCreditPoints() <= 72;} )
                          .collect(Collectors.toList());
        List<CreditCustomers> actual = service.findByCreditPointsBetween(45,72);
        assertEquals(expected, actual);
    }

    @SneakyThrows
    @Test
    void findByLastNameAndAddress_CityIn() {
        List<String> cities = Arrays.asList("Delhi", "Theni");
        List<CreditCustomers> expected = list.stream()
                .filter(c -> c.getLastName().equals("Gupta") && cities.contains(c.getAddress().getCity()))
                .collect(Collectors.toList());
        List<CreditCustomers> actual = service.findByLastNameAndAddress_CityIn("Gupta",cities);
        assertEquals(expected, actual);
    }

    @SneakyThrows
    @Test
    void lastNameAndAddressCityUsingQueryAnnotation() {

        List<String> cities = Arrays.asList("Delhi", "Theni");
        List<CreditCustomers> expected = list.stream()
                .filter(c -> c.getLastName().equals("Gupta") && cities.contains(c.getAddress().getCity()))
                .collect(Collectors.toList());
        List<CreditCustomers> actual = service.lastNameAndAddressCityUsingQueryAnnotation("Gupta",cities);
        assertEquals(expected, actual);
    }

    @AfterAll
    void afterAll() {
        list = null;
    }


}