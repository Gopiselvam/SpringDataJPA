package com.datajpa.dao;

import com.datajpa.entity.TelecomCustomer;
import com.datajpa.entity.TelecomPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public interface TelecomCustomerRepository extends JpaRepository<TelecomCustomer, Long> {


    @Transactional
    @Modifying
    @Query("update TelecomCustomer c set c.telecomPlan = ?1 where c.phoneNumber = ?2")
    Optional<?> updatePlanWherePhoneNumber(TelecomPlan plan, Long phoneNumber);
}
