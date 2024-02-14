package com.codigo.msregistro.domain.impl;

import com.codigo.msregistro.domain.aggregates.dto.PersonDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestPerson;
import com.codigo.msregistro.domain.ports.in.PersonServiceIn;
import com.codigo.msregistro.domain.ports.out.PersonServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonServiceIn {
    private final PersonServiceOut personServiceOut;
    @Override
    public PersonDTO createPersonIn(RequestPerson requestPerson) {
        return personServiceOut.createPersonOut(requestPerson);
    }

    @Override
    public Optional<PersonDTO> getPersonIn(Long id) {
        return personServiceOut.getPersonOut(id);
    }

    @Override
    public List<PersonDTO> getAllIn() {
        return personServiceOut.getAllOut();
    }

    @Override
    public PersonDTO updateIn(Long id, RequestPerson requestPerson) {
        return personServiceOut.updateOut(id,requestPerson);
    }

    @Override
    public PersonDTO deleteIn(Long id) {
        return personServiceOut.deleteOut(id);
    }

}
