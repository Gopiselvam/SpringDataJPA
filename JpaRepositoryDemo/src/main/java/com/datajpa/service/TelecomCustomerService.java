package com.datajpa.service;

import com.datajpa.dao.TelecomCustomerRepository;
import com.datajpa.dao.TelecomPlanRepository;
import com.datajpa.entity.TelecomCustomer;
import com.datajpa.entity.TelecomPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class TelecomCustomerService {

    @Autowired
    TelecomCustomerRepository customerDao;

    @Autowired
    TelecomPlanRepository planDao;


    public void updateTelecomCustomerPlan(TelecomPlan plan, Long phoneNumber){


        Optional<?> res2 =  planDao.updatePlan(plan.getPlanName(),plan.getLocalRate(), plan.getNationalRate(), plan.getPlanId());

        Object obj2;
        if(res2.isPresent()){
            obj2 = res2.get();
            if(obj2 instanceof Integer){
                System.out.println("PlanDao Integer res found. update success");
                System.out.println(obj2.toString());
            }else{
                System.out.println(" PlanDao No Integer result found");
            }
        }

        Optional<?> res = customerDao.updatePlanWherePhoneNumber(plan, phoneNumber);
        Object obj;
        if(res.isPresent()){
            obj = res.get();
            if(obj instanceof Integer){
                System.out.println("CustomerDao Integer res found. update success");
                System.out.println(obj.toString());
            }else{
                System.out.println("CustomerDao No Integer result found");
            }
        }





    }

    public TelecomCustomer findById(long id) {

        return  customerDao.findById(id)
                .orElseGet(TelecomCustomer::new);
    }
}
