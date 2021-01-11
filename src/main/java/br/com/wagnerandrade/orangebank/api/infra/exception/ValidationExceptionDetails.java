package br.com.wagnerandrade.orangebank.api.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails{
    private final String fields;
    private final String fieldsMessage;
}
