package br.com.wagnerandrade.orangebank.api.core.repository;

import br.com.wagnerandrade.orangebank.api.core.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByCpf(String cpf);

    Customer findByEmail(String email);
}
