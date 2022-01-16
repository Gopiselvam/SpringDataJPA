package com.dataJPA.jakTelSpringBoot.entity;

import com.dataJPA.jakTelSpringBoot.dto.CustomerDTO;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_jaktel")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @Column(name = "phone_no")
    private Long phoneNumber;
    private String name;
    private Integer age;
    private Character gender;
    private String address;
    @Column(name = "plan_id")
    private Integer planId;

    public static CustomerDTO prepareDTO(Customer customerEntity)
    {
        CustomerDTO custDTO = new CustomerDTO();
        custDTO.setPhoneNumber(customerEntity.getPhoneNumber());
        custDTO.setName(customerEntity.getName());
        custDTO.setGender(customerEntity.getGender());
        custDTO.setAge(customerEntity.getAge());
        custDTO.setAddress(customerEntity.getAddress());
        custDTO.setPlanId(customerEntity.getPlanId());
        return custDTO;

    }

}
