package com.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity()
@Table(name = "CREDIT_CARD_CUSTOMERS", uniqueConstraints =
        {@UniqueConstraint(columnNames = "contactNumber"), @UniqueConstraint(columnNames = "email")})
public class CreditCustomers {

    @Id
    int customerId;
    boolean active;
    int creditPoints;
    String firstName;
    String lastName;
    String contactNumber;
    String email;

    @OneToOne(targetEntity = Address.class)
    Address address;

}
