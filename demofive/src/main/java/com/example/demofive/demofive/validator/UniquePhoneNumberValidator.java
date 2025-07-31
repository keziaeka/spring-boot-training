package com.example.demofive.demofive.validator;

import com.example.demofive.demofive.repository.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, String> {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return true;
        }
        return !customerRepository.existsByPhoneNumber(phoneNumber);
    }
}
