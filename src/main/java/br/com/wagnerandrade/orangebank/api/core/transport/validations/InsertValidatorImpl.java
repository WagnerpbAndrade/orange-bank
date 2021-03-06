package br.com.wagnerandrade.orangebank.api.core.transport.validations;

import br.com.wagnerandrade.orangebank.api.core.domain.Customer;
import br.com.wagnerandrade.orangebank.api.core.repository.CustomerRepository;
import br.com.wagnerandrade.orangebank.api.core.transport.requests.CustomerPostRequestDTO;
import br.com.wagnerandrade.orangebank.api.infra.exception.ValidationExceptionDetails;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class InsertValidatorImpl implements ConstraintValidator<InsertValidator, CustomerPostRequestDTO> {

    private final CustomerRepository repository;

    @Override
    public boolean isValid(CustomerPostRequestDTO objDTO, ConstraintValidatorContext context) {
        List<ValidationExceptionDetails> list = new ArrayList<>();

        if (objDTO.getBirthdate() == null) {
            list.add(new ValidationExceptionDetails("birthdate", "Birthdate cannot be null"));
        }

        Customer customer = this.repository.findByCpf(objDTO.getCpf());
        if (customer != null) {
            list.add(new ValidationExceptionDetails("cpf", "CPF already exists"));
        }

        customer = this.repository.findByEmail(objDTO.getEmail());
        if (customer != null) {
            list.add(new ValidationExceptionDetails("email", "E-mail already exists"));
        }

        for (ValidationExceptionDetails v : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(v.getFieldsMessage()).addPropertyNode(v.getFields())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
