package br.com.wagnerandrade.orangebank.api.core.resource;

import br.com.wagnerandrade.orangebank.api.core.interfaces.CustomerService;
import br.com.wagnerandrade.orangebank.api.core.transport.CustomerDTO;
import br.com.wagnerandrade.orangebank.api.core.transport.requests.CustomerPostRequestDTO;
import br.com.wagnerandrade.orangebank.api.core.transport.requests.CustomerPutRequestDTO;
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

    private final CustomerService service;

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

    @PutMapping
    public ResponseEntity<CustomerDTO> update(@Valid @RequestBody CustomerPutRequestDTO putRequestDTO) {
        return ResponseEntity.ok().body(this.service.update(putRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
