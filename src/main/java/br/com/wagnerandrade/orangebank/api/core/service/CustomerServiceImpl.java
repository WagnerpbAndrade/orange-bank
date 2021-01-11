package br.com.wagnerandrade.orangebank.api.core.service;

import br.com.wagnerandrade.orangebank.api.core.domain.Customer;
import br.com.wagnerandrade.orangebank.api.core.interfaces.CustomerService;
import br.com.wagnerandrade.orangebank.api.core.mappers.CustomerMapper;
import br.com.wagnerandrade.orangebank.api.core.repository.CustomerRepository;
import br.com.wagnerandrade.orangebank.api.core.transport.CustomerDTO;
import br.com.wagnerandrade.orangebank.api.core.transport.requests.CustomerPostRequestDTO;
import br.com.wagnerandrade.orangebank.api.core.transport.requests.CustomerPutRequestDTO;
import br.com.wagnerandrade.orangebank.api.infra.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper = CustomerMapper.INSTANCE;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CustomerDTO findByIdOrThrowBadRequestException(Long id) {
        return this.mapper
                .toCustomerDTO(this.repository
                        .findById(id)
                        .orElseThrow(() -> new BadRequestException("Customer not found")));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CustomerDTO> findAll() {
        return this.repository.findAll().stream()
                .map(this.mapper::toCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerDTO save(CustomerPostRequestDTO postRequestDTO) {
        Customer customer = this.mapper.toCustomer(postRequestDTO);
        return this.mapper.toCustomerDTO(this.repository.save(customer));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerDTO update(CustomerPutRequestDTO putRequestDTO) {
        Customer customer = this.mapper.toCustomer(this.findByIdOrThrowBadRequestException(putRequestDTO.getId()));

        updateData(customer, putRequestDTO);

        return this.mapper.toCustomerDTO(this.repository.save(customer));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Long customerId = this.mapper.toCustomer(this.findByIdOrThrowBadRequestException(id)).getId();
        this.repository.deleteById(customerId);
    }

    private void updateData(Customer customer, CustomerPutRequestDTO putRequestDTO) {
        customer.setName(putRequestDTO.getName());
        customer.setEmail(putRequestDTO.getEmail());
        customer.setCpf(putRequestDTO.getCpf());
        customer.setBirthdate(putRequestDTO.getBirthdate());
    }
}
