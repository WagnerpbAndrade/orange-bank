package br.com.wagnerandrade.orangebank.api.core.mappers;

import br.com.wagnerandrade.orangebank.api.core.domain.Customer;
import br.com.wagnerandrade.orangebank.api.core.transport.CustomerDTO;
import br.com.wagnerandrade.orangebank.api.core.transport.requests.CustomerPostRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "orange")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toCustomer(CustomerDTO customerDTO);

    Customer toCustomer(CustomerPostRequestDTO postRequestDTO);

    CustomerDTO toCustomerDTO(Customer customer);
}
