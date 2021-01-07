package br.com.wagnerandrade.orangebank.api.core.service;

import br.com.wagnerandrade.orangebank.api.core.domain.Customer;
import br.com.wagnerandrade.orangebank.api.core.interfaces.ICustomerService;
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
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository repository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CustomerDTO findByIdOrThrowBadRequestException(Long id) {
        return CustomerMapper.INSTANCE
                .toCustomerDTO(this.repository
                        .findById(id)
                        .orElseThrow(() -> new BadRequestException("Customer not found")));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CustomerDTO> findAll() {
        return this.repository.findAll().stream()
                .map(CustomerMapper.INSTANCE::toCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerDTO save(CustomerPostRequestDTO postRequestDTO) {
        Customer customer = CustomerMapper.INSTANCE.toCustomer(postRequestDTO);
        return CustomerMapper.INSTANCE.toCustomerDTO(this.repository.save(customer));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CustomerPutRequestDTO putRequestDTO) {
        Customer customer = CustomerMapper.INSTANCE.toCustomer(this.findByIdOrThrowBadRequestException(putRequestDTO.getId()));

        updateData(customer, putRequestDTO);

        this.repository.save(customer);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Long customerId = CustomerMapper.INSTANCE.toCustomer(this.findByIdOrThrowBadRequestException(id)).getId();
        this.repository.deleteById(customerId);
    }

    private void updateData(Customer customer, CustomerPutRequestDTO putRequestDTO) {
        customer.setName(putRequestDTO.getName());
        customer.setEmail(putRequestDTO.getEmail());
        customer.setCpf(putRequestDTO.getCpf());
        customer.setBirthdate(putRequestDTO.getBirthdate());
    }
}
