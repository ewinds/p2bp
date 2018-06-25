package io.github.ewinds.service.dto;

import io.github.ewinds.domain.Product;

public class ProductDTO extends Product {
    private CustomerDTO customer;

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public ProductDTO() {

    }

    public ProductDTO customer(CustomerDTO customer) {
        this.customer = customer;
        return this;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
            "customer=" + customer +
            '}';
    }
}
