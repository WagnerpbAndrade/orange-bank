package br.com.wagnerandrade.orangebank.api.core.repository;

import br.com.wagnerandrade.orangebank.api.core.domain.Customer;
import br.com.wagnerandrade.orangebank.api.util.CustomerCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@DisplayName("Tests for Customer Repository")
@AutoConfigureTestDatabase(replace = NONE)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    @DisplayName("save persists customer when successful")
    void save_PersistCustomer_WhenSuccessful() {
        Customer customerToBeSave = CustomerCreator.createCustomerToBeSave();

        Customer customerSaved = this.repository.save(customerToBeSave);

        Assertions.assertThat(customerSaved).isNotNull();

        Assertions.assertThat(customerSaved.getId()).isNotNull();

        Assertions.assertThat(customerSaved.getName()).isEqualTo(customerToBeSave.getName());

        Assertions.assertThat(customerSaved.getEmail()).isEqualTo(customerToBeSave.getEmail());

        Assertions.assertThat(customerSaved.getCpf()).isEqualTo(customerToBeSave.getCpf());

        Assertions.assertThat(customerSaved.getBirthdate()).isEqualTo(customerToBeSave.getBirthdate());
    }

    @Test
    @DisplayName("save updates customer when successful")
    void save_UpdatesCustomer_WhenSuccessful() {
        Customer customerToBeSaved = CustomerCreator.createCustomerToBeUpdate();

        Customer customerSaved = this.repository.save(customerToBeSaved);

        customerSaved.setName("Customer 1 updated");

        Customer customerUpdated = this.repository.save(customerSaved);

        Assertions.assertThat(customerUpdated).isNotNull();

        Assertions.assertThat(customerUpdated.getId()).isNotNull();

        Assertions.assertThat(customerUpdated.getName()).isEqualTo(customerToBeSaved.getName());

        Assertions.assertThat(customerUpdated.getEmail()).isEqualTo(customerToBeSaved.getEmail());

        Assertions.assertThat(customerUpdated.getCpf()).isEqualTo(customerToBeSaved.getCpf());

        Assertions.assertThat(customerUpdated.getBirthdate()).isEqualTo(customerToBeSaved.getBirthdate());
    }

    @Test
    @DisplayName("delete removes customer when successful")
    void delete_RemovesCustomer_WhenSuccessfull() {
        Customer customerToBeSave = CustomerCreator.createCustomerToBeSave();

        Customer customerSaved = this.repository.save(customerToBeSave);

        this.repository.delete(customerSaved);

        Optional<Customer> customerOptional = this.repository.findById(customerSaved.getId());

        Assertions.assertThat(customerOptional).isEmpty();
    }

    @Test
    @DisplayName("findByCpf returns customer when successful")
    void findByCpf_ReturnsCustomer_WhenSuccessful() {
        Customer customerToBeSave = CustomerCreator.createCustomerToBeSave();

        Customer customerSaved = this.repository.save(customerToBeSave);

        String cpf = customerSaved.getCpf();

        Customer customer = this.repository.findByCpf(cpf);

        Assertions.assertThat(customer)
                .isNotNull();
    }

    @Test
    @DisplayName("findByEmail returns customer when successful")
    void findByEmail_ReturnsCustomer_WhenSuccessful() {
        Customer customerToBeSave = CustomerCreator.createCustomerToBeSave();

        Customer customerSaved = this.repository.save(customerToBeSave);

        String email = customerSaved.getEmail();

        Customer customer = this.repository.findByEmail(email);

        Assertions.assertThat(customer)
                .isNotNull();
    }

    @Test
    @DisplayName("findAll returns list of customer when successful")
    void findAll_ReturnsListOfCustomer_WhenSuccessful() {
        Customer customerToBeSave = CustomerCreator.createCustomerToBeSave();

        Customer customerSaved = this.repository.save(customerToBeSave);

        List<Customer> customers = this.repository.findAll();

        Assertions.assertThat(customers)
                .isNotEmpty()
                .contains(customerSaved);
    }

    @Test
    @DisplayName("save thow ConstraintViolationException when name is empty")
    void save_ThrowConstraintViolationException_WhenNameIsEmpty() {
        Customer customerToBeSave = CustomerCreator.createCustomerNameEmpty();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.repository.save(customerToBeSave))
                .withMessageContaining("Name cannot be empty");
    }

    @Test
    @DisplayName("save thow ConstraintViolationException when email is empty")
    void save_ThrowConstraintViolationException_WhenEmailIsEmpty() {
        Customer customerToBeSave = CustomerCreator.createCustomerEmailEmpty();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.repository.save(customerToBeSave))
                .withMessageContaining("Email cannot be empty");
    }

    @Test
    @DisplayName("save thow ConstraintViolationException when email is invalid")
    void save_ThrowConstraintViolationException_WhenEmailIsInvalid() {
        Customer customerToBeSave = CustomerCreator.createCustomerEmailInvalid();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.repository.save(customerToBeSave))
                .withMessageContaining("Email cannot be invalid");
    }

    @Test
    @DisplayName("save thow ConstraintViolationException when cpf is null")
    void save_ThrowConstraintViolationException_WhenCPFIsNull() {
        Customer customerToBeSave = CustomerCreator.createCustomerCPFIsNull();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.repository.save(customerToBeSave))
                .withMessageContaining("CPF cannot be null");
    }

    @Test
    @DisplayName("save thow ConstraintViolationException when cpf is invalid")
    void save_ThrowConstraintViolationException_WhenCPFIsInvalid() {
        Customer customerToBeSave = CustomerCreator.createCustomerCPFInvalid();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.repository.save(customerToBeSave))
                .withMessageContaining("CPF cannot be invalid");
    }
}