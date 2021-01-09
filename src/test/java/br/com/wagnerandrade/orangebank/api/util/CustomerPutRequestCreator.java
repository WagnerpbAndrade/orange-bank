package br.com.wagnerandrade.orangebank.api.util;

import br.com.wagnerandrade.orangebank.api.core.transport.requests.CustomerPutRequestDTO;

public class CustomerPutRequestCreator {

    public static CustomerPutRequestDTO createCustomerPutRequestDTO() {
        return CustomerPutRequestDTO.builder()
                .id(CustomerCreator.createCustomerToBeUpdate().getId())
                .name(CustomerCreator.createCustomerToBeUpdate().getName())
                .email(CustomerCreator.createCustomerToBeUpdate().getEmail())
                .cpf(CustomerCreator.createCustomerToBeUpdate().getCpf())
                .birthdate(CustomerCreator.createCustomerToBeUpdate().getBirthdate())
                .build();
    }
}
