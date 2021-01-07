package br.com.wagnerandrade.orangebank.api.core.transport.requests;

import br.com.wagnerandrade.orangebank.api.core.transport.validations.ICPFInsertValidator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ICPFInsertValidator
public class CustomerPostRequestDTO {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Email(message = "Email cannot be invalid")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @CPF(message = "CPF cannot be invalid")
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthdate;
}