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
public class TelecomPlan {
    @Id
    @Column(name = "plan_id")
    private Integer planId;
    @Column(name = "plan_name")
    private String planName;
    @Column(name = "local_rate")
    private Integer localRate;
    @Column(name = "national_rate")
    private Integer nationalRate;

}
