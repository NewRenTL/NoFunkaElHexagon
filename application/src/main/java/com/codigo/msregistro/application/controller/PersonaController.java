package com.codigo.msregistro.application.controller;

import com.codigo.msregistro.domain.aggregates.dto.PersonDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestPerson;
import com.codigo.msregistro.domain.ports.in.PersonServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/person")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonServiceIn personServiceIn;

    @PostMapping
    public ResponseEntity<PersonDTO> registrar(@RequestBody RequestPerson requestPersona){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personServiceIn.createPersonIn(requestPersona));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO>obtenerPersona(@PathVariable Long id){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personServiceIn.getPersonIn(id).get());

    }
    @GetMapping()
    public ResponseEntity<List<PersonDTO>>getAll(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personServiceIn.getAllIn());

    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO>update(@PathVariable Long id,@RequestBody RequestPerson requestPersona){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personServiceIn.updateIn(id,requestPersona));

    }
}


