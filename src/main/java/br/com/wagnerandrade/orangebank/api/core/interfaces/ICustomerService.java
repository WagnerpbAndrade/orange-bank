package br.com.wagnerandrade.orangebank.api.core.interfaces;

import br.com.wagnerandrade.orangebank.api.core.transport.CustomerDTO;
import br.com.wagnerandrade.orangebank.api.core.transport.requests.CustomerPostRequestDTO;
import br.com.wagnerandrade.orangebank.api.core.transport.requests.CustomerPutRequestDTO;

import java.util.List;

public interface ICustomerService {

    CustomerDTO findByIdOrThrowBadRequestException(Long id);

    List<CustomerDTO> findAll();

    CustomerDTO save(CustomerPostRequestDTO postRequestDTO);

    CustomerDTO update(CustomerPutRequestDTO putRequestDTO);

    void delete(Long id);
}
