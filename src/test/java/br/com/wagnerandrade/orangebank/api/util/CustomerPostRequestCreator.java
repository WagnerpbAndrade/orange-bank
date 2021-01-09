package br.com.wagnerandrade.orangebank.api.util;

import br.com.wagnerandrade.orangebank.api.core.transport.requests.CustomerPostRequestDTO;

public class CustomerPostRequestCreator {

    public static CustomerPostRequestDTO createCustomerPostRequestDTO() {
        return CustomerPostRequestDTO.builder()
                .name(CustomerCreator.createCustomerNameEmpty().getName())
                .email(CustomerCreator.createValidCustomer().getEmail())
                .cpf(CustomerCreator.createValidCustomer().getCpf())
                .birthdate(CustomerCreator.createValidCustomer().getBirthdate())
                .build();
    }
}
