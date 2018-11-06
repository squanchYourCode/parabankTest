package com.parasoft.parabank.web;

import com.parasoft.parabank.domain.*;

/**
 * Object for storing logged in user session data
 */
public class UserSession {
    private Customer customer;

    public UserSession(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
