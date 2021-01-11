package br.com.wagnerandrade.orangebank.api.core.transport.validations;

import br.com.wagnerandrade.orangebank.api.core.domain.Customer;
import br.com.wagnerandrade.orangebank.api.core.repository.CustomerRepository;
import br.com.wagnerandrade.orangebank.api.core.transport.requests.CustomerPutRequestDTO;
import br.com.wagnerandrade.orangebank.api.infra.exception.ValidationExceptionDetails;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UpdateValidatorImpl implements ConstraintValidator<UpdateValidator, CustomerPutRequestDTO> {

    private final CustomerRepository repository;

    @Override
    public boolean isValid(CustomerPutRequestDTO objDTO, ConstraintValidatorContext context) {
        List<ValidationExceptionDetails> list = new ArrayList<>();

        if (objDTO.getId() == null) {
            list.add(new ValidationExceptionDetails("id", "Id cannot be null"));
        }

        if (objDTO.getBirthdate() == null) {
            list.add(new ValidationExceptionDetails("birthdate", "Birthdate cannot be null"));
        }

        Customer customer = this.repository.findByCpf(objDTO.getCpf());
        if (customer != null && !customer.getId().equals(objDTO.getId())) {
            list.add(new ValidationExceptionDetails("cpf", "CPF already exists"));
        }

        customer = this.repository.findByEmail(objDTO.getEmail());
        if (customer != null && !customer.getId().equals(objDTO.getId())) {
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
