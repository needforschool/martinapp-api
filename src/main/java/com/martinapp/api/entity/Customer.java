package com.martinapp.api.entity;

import com.martinapp.api.common.Address;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Customer {
    @Getter
    @Setter
    private @Id @GeneratedValue UUID id;

    @Getter
    @Setter
    private String firstName, lastName, phoneNumber;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "address_id")
    @Resource(name = "Address", type = Address.class)
    private Address address;

    @Getter
    @Setter
    private Date dateOfBirth;

    public Customer() {}

    public Customer(String firstName, String lastName, Date dateOfBirth, Address address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public void setName(String name) {
        String[] names = name.split(" ");
        this.firstName = names[0];
        this.lastName = names[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;
        return Objects.equals(this.id, customer.id) &&
                Objects.equals(this.firstName, customer.firstName) &&
                Objects.equals(this.lastName, customer.lastName) &&
                Objects.equals(this.dateOfBirth, customer.dateOfBirth) &&
                Objects.equals(this.phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.dateOfBirth, this.phoneNumber);
    }

    @Override
    public String toString() {
        return String.format("Customer{id=%s, firstName=%s, lastName=%s, dateOfBirth=%s, phoneNumber=%s}",
                this.id, this.firstName, this.lastName, this.dateOfBirth, this.phoneNumber);
    }
}
