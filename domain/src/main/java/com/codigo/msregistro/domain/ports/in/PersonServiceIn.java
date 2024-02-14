package com.codigo.msregistro.domain.ports.in;

import com.codigo.msregistro.domain.aggregates.dto.PersonDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestPerson;

import java.util.List;
import java.util.Optional;


public interface PersonServiceIn {
    PersonDTO createPersonIn(RequestPerson requestPerson);

    Optional<PersonDTO> getPersonIn(Long id);

    List<PersonDTO> getAllIn();

    PersonDTO updateIn(Long id,RequestPerson requestPerson);

    PersonDTO deleteIn(Long id);
}
