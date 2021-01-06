package br.com.wagnerandrade.orangebank.api.core.resource;

import br.com.wagnerandrade.orangebank.api.core.interfaces.ICustomerService;
import br.com.wagnerandrade.orangebank.api.core.transport.CustomerDTO;
import br.com.wagnerandrade.orangebank.api.core.transport.requests.CustomerPostRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerResource {

    private final ICustomerService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.service.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll() {
        return ResponseEntity.ok().body(this.service.findAll());
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> insert(@Valid @RequestBody CustomerPostRequestDTO postRequestDTO) {
        return new ResponseEntity(this.service.save(postRequestDTO), HttpStatus.CREATED);
    }
}
