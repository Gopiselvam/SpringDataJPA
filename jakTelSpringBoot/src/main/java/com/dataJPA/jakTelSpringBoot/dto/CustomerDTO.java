package com.dataJPA.jakTelSpringBoot.dto;

import com.dataJPA.jakTelSpringBoot.entity.Customer;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CustomerDTO{
    private Long phoneNumber;
    private String name;
    private Integer age;
    private Character gender;
    private String address;
    private Integer planId;


   public CustomerDTO(CustomerDTO customerDTO){
       this.phoneNumber = customerDTO.getPhoneNumber();
       this.name = customerDTO.getName();
       this.age = customerDTO.getAge();
       this.gender = customerDTO.getGender();
       this.address = customerDTO.getAddress();
       this.planId = customerDTO.getPlanId();
   }



    public static Customer prepareCustomerEntity(CustomerDTO customerDTO)
    {
        Customer customerEntity = new Customer();
        customerEntity.setPhoneNumber(customerDTO.getPhoneNumber());
        customerEntity.setName(customerDTO.getName());
        customerEntity.setGender(customerDTO.getGender());
        customerEntity.setAge(customerDTO.getAge());
        customerEntity.setAddress(customerDTO.getAddress());
        customerEntity.setPlanId(customerDTO.getPlanId());
        return customerEntity;

    }
}
