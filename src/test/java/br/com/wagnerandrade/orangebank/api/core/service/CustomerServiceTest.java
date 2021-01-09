package br.com.wagnerandrade.orangebank.api.core.service;

import br.com.wagnerandrade.orangebank.api.core.domain.Customer;
import br.com.wagnerandrade.orangebank.api.core.mappers.CustomerMapper;
import br.com.wagnerandrade.orangebank.api.core.repository.CustomerRepository;
import br.com.wagnerandrade.orangebank.api.core.transport.requests.CustomerPutRequestDTO;
import br.com.wagnerandrade.orangebank.api.util.CustomerCreator;
import br.com.wagnerandrade.orangebank.api.util.CustomerPostRequestCreator;
import br.com.wagnerandrade.orangebank.api.util.CustomerPutRequestCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Customer Service")
class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl service;

    @Mock
    private CustomerRepository repository;

    @BeforeEach
    void setUp() {
        BDDMockito.when(this.repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(CustomerCreator.createValidCustomer()));

        BDDMockito.when(this.repository.findAll())
                .thenReturn(List.of(CustomerCreator.createValidCustomer()));

        BDDMockito.when(this.repository.save(ArgumentMatchers.any(Customer.class)))
                .thenReturn(CustomerCreator.createValidCustomer());

        BDDMockito.doNothing().when(this.repository).deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException returns customer when successful")
    void findByIdOrThrowBadRequestException_ReturnsCustormer_WhenSuccessful() {
        String expectedName = CustomerCreator.createValidCustomer().getName();

        Customer customer = CustomerMapper.INSTANCE.toCustomer(this.service.findByIdOrThrowBadRequestException(1L));

        Assertions.assertThat(customer)
                .isNotNull();

        Assertions.assertThat(customer.getName())
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findAll returns a list of customer when successful")
    void findAll_ReturnsListOfCustomer_WhenSuccessful() {
        String expectedName = CustomerCreator.createValidCustomer().getName();

        List<Customer> customers = this.service.findAll().stream()
                .map(CustomerMapper.INSTANCE::toCustomer)
                .collect(Collectors.toList());

        Assertions.assertThat(customers)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(customers.get(0).getName())
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(expectedName);
    }

    @Test
    @DisplayName("save returns customer when successful")
    void save_ReturnsCustomer_WhenSuccessful() {
        String expectedName = CustomerCreator.createValidCustomer().getName();

        Customer customer = CustomerMapper.INSTANCE.toCustomer(this.service.save(CustomerPostRequestCreator.createCustomerPostRequestDTO()));

        Assertions.assertThat(customer)
                .isNotNull();

        Assertions.assertThat(customer.getName())
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(expectedName);
    }

    @Test
    @DisplayName("update updates customer when successful")
    void update_UpdatesCustomer_WhenSuccessful() {
        String expectedName = CustomerCreator.createValidCustomer().getName();

        BDDMockito.when(this.repository.save(ArgumentMatchers.any(Customer.class)))
                .thenReturn(CustomerCreator.createValidCustomer());

        CustomerPutRequestDTO putRequestDTO = CustomerPutRequestCreator.createCustomerPutRequestDTO();

        Customer customer = CustomerMapper.INSTANCE.toCustomer(this.service.update(putRequestDTO));

        Assertions.assertThat(customer)
                .isNotNull();

        Assertions.assertThat(customer.getName())
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(expectedName);

    }

    @Test
    @DisplayName("delete removes customer when successful")
    void delete_RemovesCustomer_WhenSuccessful() {
        Assertions.assertThatCode(() -> this.service.delete(1L))
                .doesNotThrowAnyException();
    }
}