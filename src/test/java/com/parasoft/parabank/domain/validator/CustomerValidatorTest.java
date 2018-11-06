package com.parasoft.parabank.domain.validator;

import org.springframework.validation.*;

import com.parasoft.parabank.domain.*;

public class CustomerValidatorTest extends AbstractValidatorTest {
    public CustomerValidatorTest() {
        super(Customer.class, new String[] { "firstName", "lastName", "ssn", "username", "password" });
    }

    @Override
    protected Validator getValidator() {
        CustomerValidator validator = new CustomerValidator();
        validator.setAddressValidator(new AddressValidator());
        return validator;
    }
}
