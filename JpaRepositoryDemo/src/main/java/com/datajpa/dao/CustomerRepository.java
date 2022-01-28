package com.datajpa.dao;

import com.datajpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<List<Customer>> findByPlanId(Integer planId);

    Optional<List<Customer>> fetchUsingAddress(String address);

    @Query(value = "select * from customer_jaktel where age > 80;", nativeQuery = true)
    Optional<List<Customer>> fetchCustomerAgeGT88(int age);
}
