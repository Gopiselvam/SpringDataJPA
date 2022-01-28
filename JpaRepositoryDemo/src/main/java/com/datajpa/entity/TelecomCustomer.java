package com.datajpa.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class TelecomCustomer {
    @Id
    @Column(name = "phone_no")
    private Long phoneNumber;
    private String name;
    private Integer age;
    private Character gender;
    private String address;
    private String currentPlan;

    @OneToOne(targetEntity = TelecomPlan.class)
    private TelecomPlan telecomPlan;
}
