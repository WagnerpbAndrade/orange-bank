package br.com.wagnerandrade.orangebank.api.util;

import br.com.wagnerandrade.orangebank.api.core.domain.Customer;

import java.time.LocalDate;

public class CustomerCreator {
    private final static LocalDate BIRTHDATE = LocalDate.now();

    public static Customer createCustomerToBeSave() {
        return Customer.builder()
                .name("Customer 1")
                .email("customer1@gmail.com")
                .cpf("80320789080")
                .birthdate(BIRTHDATE)
                .build();
    }

    public static Customer createCustomerToBeUpdate() {
        return Customer.builder()
                .id(1L)
                .name("Customer 1 updated")
                .email("customer1@gmail.com")
                .cpf("88197762007")
                .birthdate(BIRTHDATE)
                .build();
    }

    public static Customer createValidCustomer() {
        return Customer.builder()
                .id(1L)
                .name("Customer 1")
                .email("customer1@gmail.com")
                .cpf("88197762007")
                .birthdate(BIRTHDATE)
                .build();
    }

    public static Customer createCustomerNameEmpty() {
        return Customer.builder()
                .id(1L)
                .name("")
                .email("customer1@gmail.com")
                .cpf("88197762007")
                .birthdate(BIRTHDATE)
                .build();
    }

    public static Customer createCustomerEmailEmpty() {
        return Customer.builder()
                .id(1L)
                .name("Customer 1")
                .email("")
                .cpf("88197762007")
                .birthdate(BIRTHDATE)
                .build();
    }

    public static Customer createCustomerCPFIsNull() {
        return Customer.builder()
                .id(1L)
                .name("Customer 1")
                .email("customer1@gmail.com")
                .cpf(null)
                .birthdate(BIRTHDATE)
                .build();
    }

    public static Customer createCustomerCPFInvalid() {
        return Customer.builder()
                .id(1L)
                .name("Customer 1")
                .email("customer1@gmail.com")
                .cpf("234")
                .birthdate(BIRTHDATE)
                .build();
    }

    public static Customer createCustomerEmailInvalid() {
        return Customer.builder()
                .id(1L)
                .name("Customer 1")
                .email("customer1gmail.com")
                .cpf("88197762007")
                .birthdate(BIRTHDATE)
                .build();
    }
}
