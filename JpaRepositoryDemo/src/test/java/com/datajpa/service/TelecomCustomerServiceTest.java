package com.datajpa.service;

import com.datajpa.entity.TelecomCustomer;
import com.datajpa.entity.TelecomPlan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SqlGroup({
        @Sql(scripts = {"/import_telecom_customers.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = {"/truncate_telecom_customer.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class TelecomCustomerServiceTest {

    @Autowired
    TelecomCustomerService service;

    @Test
    void updateTelecomCustomerPlan() {

        TelecomPlan plan = new TelecomPlan(2, "TEL_200", 200, 200);
        service.updateTelecomCustomerPlan(plan, 9874563210L);

        TelecomPlan telecomPlan = new TelecomPlan(2, "TEL_200", 200, 200);
        TelecomCustomer customer = new TelecomCustomer(9874563210L, "Jacky", 54, 'F', "HongKong", "TEL_50", telecomPlan);

        assertEquals(customer, service.findById(9874563210L));

    }
}