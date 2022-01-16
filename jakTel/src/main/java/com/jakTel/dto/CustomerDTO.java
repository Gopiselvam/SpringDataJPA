package com.jakTel.dto;

import com.jakTel.entity.Customer;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CustomerDTO {
    private Long phoneNumber;
    private String name;
    private Integer age;
    private Character gender;
    private String address;
    private Integer planId;

    public static Customer prepareCustomerEntity(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setName(customerDTO.getName());
        customer.setAge(customerDTO.getAge());
        customer.setGender(customerDTO.getGender());
        customer.setAddress(customerDTO.getAddress());
        customer.setPlanId(customerDTO.getPlanId());
        return customer;
    }
}
