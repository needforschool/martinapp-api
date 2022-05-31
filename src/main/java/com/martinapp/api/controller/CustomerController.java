package com.martinapp.api.controller;

import com.martinapp.api.assembler.CustomerModelAssembler;
import com.martinapp.api.entity.Customer;
import com.martinapp.api.exception.CustomerNotFoundException;
import com.martinapp.api.exception.InvalidUUIDException;
import com.martinapp.api.repository.CustomerRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController()
public class CustomerController {
    private final CustomerRepository repository;
    private final CustomerModelAssembler assembler;

    public CustomerController(CustomerRepository repository, CustomerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Customer>> getCustomers() {
        List<EntityModel<Customer>> customers = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(customers,
                linkTo(methodOn(CustomerController.class).getCustomers()).withSelfRel());
    }

    @GetMapping("/customers/{id}")
    public EntityModel<Customer> getCustomer(@PathVariable String id) {
        // - Check if the id is a valid UUID
        Pattern pattern = Pattern.compile("^[\\da-fA-F]{8}-[\\da-fA-F]{4}-[\\da-fA-F]{4}-[\\da-fA-F]{4}-[\\da-fA-F]{12}$");
        if (!pattern.matcher(id).matches()) {
            throw new InvalidUUIDException(id);
        }

        return getCustomer(UUID.fromString(id));
    }

    private EntityModel<Customer> getCustomer(UUID id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

        return assembler.toModel(customer);
    }
}
