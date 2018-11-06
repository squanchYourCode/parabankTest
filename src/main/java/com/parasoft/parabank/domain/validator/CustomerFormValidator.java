package com.parasoft.parabank.domain.validator;

import javax.annotation.*;

import org.springframework.validation.*;

import com.parasoft.parabank.domain.*;
import com.parasoft.parabank.web.form.*;

/**
 * Provides basic empty field validation for Customer object
 */
public class CustomerFormValidator implements Validator {
    @Resource(name = "customerValidator")
    private Validator customerValidator;

    public void setCustomerValidator(final Validator addressValidator) {
        customerValidator = addressValidator;
    }

    @Override
    public boolean supports(final Class<?> clazz) {
        return CustomerForm.class.isAssignableFrom(clazz) || Customer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {
        if (obj instanceof Customer) {
            customerValidator.validate(obj, errors);
        } else {
            final CustomerForm customerForm = (CustomerForm) obj;
            final Customer customer = customerForm.getCustomer();
            try {
                errors.pushNestedPath("customer");
                ValidationUtils.invokeValidator(customerValidator, customer, errors);
            } finally {
                errors.popNestedPath();
            }
            if (customerForm.getRepeatedPassword() == null || customerForm.getRepeatedPassword().length() <= 0) {
                errors.rejectValue("repeatedPassword", "error.password.confirmation.required");
            } else if (customerForm.getCustomer().getPassword() != null
                && customerForm.getCustomer().getPassword().length() > 0
                && !customer.getPassword().equals(customerForm.getRepeatedPassword())) {
                errors.rejectValue("repeatedPassword", "error.password.mismatch");
            }
        }

    }
}
