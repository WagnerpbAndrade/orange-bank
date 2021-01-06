package br.com.wagnerandrade.orangebank.api.index;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexResource {

    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok().body("API Rest Orange Bank");
    }
}
