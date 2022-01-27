package com.datajpa.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity()
@Table(name = "CREDIT_CARD_CUSTOMERS_ADDRESS")
public class Address {
    @Id
    private int addressId;
	private String city;
	private String pincode;
}
