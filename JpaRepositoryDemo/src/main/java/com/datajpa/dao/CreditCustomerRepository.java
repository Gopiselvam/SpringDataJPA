package com.datajpa.dao;

import com.datajpa.entity.CreditCustomers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCustomerRepository extends JpaRepository<CreditCustomers, Integer> {

    // select c from CreditCustomers c where c.email = ?
    Optional<CreditCustomers> findByEmail(String email);

    // select c from CreditCustomers c where c.lastName like CONCAT('%', ? , '%')
    Optional<List<CreditCustomers>> findByLastNameLike(String lastName);

    // select c from CreditCustomers c where c.email = ?1 or c.contactNumber = ?2
    Optional<List<CreditCustomers>> findByEmailOrContactNumber(String email, String contactNumber);

    // select c from CreditCustomers c where c.lastName = ? order by c.firstName
    Optional<List<CreditCustomers>> findByLastNameOrderByFirstNameDesc(String lastName);

    // select c from CreditCustomers c where c.active = true
    Optional<List<CreditCustomers>> findByActiveFalse();

    // select c from CreditCustomers c where c.creditPoints >= ?1
    Optional<List<CreditCustomers>> findByCreditPointsGreaterThanEqual(int cPoint);

    // select c from CreditCustomers c where c.creditPoints between ?1 and ?2
    Optional<List<CreditCustomers>> findByCreditPointsBetween(int c1point, int c2point);

    // select c from CreditCustomers c where c.lastName = ?1 and c.address.city in ?2
    Optional<List<CreditCustomers>> findByLastNameAndAddress_CityIn(String lastName, Collection<String> cities);

    @Query("select c from CreditCustomers c where c.lastName = ?1 and c.address.city in ?2")
    Optional<List<CreditCustomers>> lastNameAndAddressCityUsingQueryAnnotation(String lastName, Collection<String> cities);



}