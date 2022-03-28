package com.fpt.mock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column(name = "email", unique = true, columnDefinition = "text")
    private String email;

    @Column(name = "password", columnDefinition = "text")
    private String password;

    @Column(name = "name", columnDefinition = "text")
    private String name;

    @Column(name = "address", columnDefinition = "text")
    private String address;

    @Column(name = "phone", columnDefinition = "text")
    private String phone;

}
