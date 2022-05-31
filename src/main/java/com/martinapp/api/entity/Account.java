package com.martinapp.api.entity;

import com.martinapp.api.exception.InsufficientFundsException;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.UUID;

@Entity
public class Account {
    @Getter
    private @Id @GeneratedValue UUID id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @Resource(name = "Customer", type = Customer.class)
    private Customer holder;

    @Getter
    @Setter
    private Double balance;

    @Getter
    @Setter
    private Double authorizedOverdraft;

    public Account() {}

    public Account(Customer holder, Double balance, Double authorizedOverdraft) {
        this.holder = holder;
        this.balance = balance;
        this.authorizedOverdraft = authorizedOverdraft;
    }

    public void increaseBalance(Double amount) {
        this.balance += amount;
    }

    public void decreaseBalance(Double amount) {
        if (this.balance - amount > this.authorizedOverdraft) {
            this.balance -= amount;
        } else {
            throw new InsufficientFundsException("You cannot exceed your authorized overdraft");
        }
    }
}
