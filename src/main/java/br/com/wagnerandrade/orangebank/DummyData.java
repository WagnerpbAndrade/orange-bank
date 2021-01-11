package br.com.wagnerandrade.orangebank;

import br.com.wagnerandrade.orangebank.api.core.domain.Customer;
import br.com.wagnerandrade.orangebank.api.core.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DummyData implements CommandLineRunner {

    private final CustomerRepository repository;

    @Override
    public void run(String... args) throws Exception {
        this.repository.deleteAll();
        List<Customer> customers = List.of(
                Customer.builder()
                        .name("Customer A")
                        .email("customerA@gmail.com")
                        .cpf("63396382013")
                        .birthdate(LocalDate.now())
                        .build(),

                Customer.builder()
                        .name("Customer B")
                        .email("customerB@gmail.com")
                        .cpf("42382396016")
                        .birthdate(LocalDate.now())
                        .build(),

                Customer.builder()
                        .name("Customer C")
                        .email("customerC@gmail.com")
                        .cpf("95540193008")
                        .birthdate(LocalDate.now())
                        .build(),

                Customer.builder()
                        .name("Customer D")
                        .email("customeD@gmail.com")
                        .cpf("85488578030")
                        .birthdate(LocalDate.now())
                        .build(),

                Customer.builder()
                        .name("Customer E")
                        .email("customeE@gmail.com")
                        .cpf("63396382013")
                        .birthdate(LocalDate.now())
                        .build()
        );
        this.repository.saveAll(customers);
    }
}
