package com.datajpa.dao;

import com.datajpa.entity.TelecomPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

public interface TelecomPlanRepository extends JpaRepository<TelecomPlan, Integer> {

    @Transactional
    @Modifying
    @Query("update TelecomPlan c set c.planName = ?1, c.localRate = ?2, c.nationalRate = ?3 where c.planId = ?4")
    Optional<?> updatePlan(String planName, Integer localRate, Integer nationalRate, Integer planId);

    @Transactional
    @Modifying
    @Query("update TelecomPlan c set c.planName = 1, c.localRate = ab, c.nationalRate = ba where c.planId = 2")
    Optional<?> updatePlanWrongQuery();
}
