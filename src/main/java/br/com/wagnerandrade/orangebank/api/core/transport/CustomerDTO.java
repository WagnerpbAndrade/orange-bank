package br.com.wagnerandrade.orangebank.api.core.transport;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {

    private Long id;

    private String name;

    private String email;

    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthdate;
}
