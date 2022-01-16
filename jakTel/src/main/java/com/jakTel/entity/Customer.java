package com.jakTel.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Customer {
    private Long phoneNumber;
    private String name;
    private Integer age;
    private Character gender;
    private String address;
    private Integer planId;
}
