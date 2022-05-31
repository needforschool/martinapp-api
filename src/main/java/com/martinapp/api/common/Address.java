package com.martinapp.api.common;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {
    @Getter
    private @Id @GeneratedValue Long id;

    @Getter
    private String street, city, state, zip;

    public Address(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Address() {
    }

    @Override
    public String toString() {
        return String.format("Address{street='%s', city='%s', state='%s', zip='%s'}", street, city, state, zip);
    }
}
