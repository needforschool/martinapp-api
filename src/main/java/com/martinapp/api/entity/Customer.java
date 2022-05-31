package com.martinapp.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private Date dateOfBirth;

    public Customer() {}

    public Customer(String firstName, String lastName, Date dateOfBirth, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
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
