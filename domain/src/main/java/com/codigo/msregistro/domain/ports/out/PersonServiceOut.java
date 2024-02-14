package com.codigo.msregistro.domain.ports.out;

import com.codigo.msregistro.domain.aggregates.dto.PersonDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestPerson;

import java.util.List;
import java.util.Optional;

public interface PersonServiceOut {
    PersonDTO createPersonOut(RequestPerson requestPerson);

    Optional<PersonDTO> getPersonOut(Long id);

    List<PersonDTO> getAllOut();

    PersonDTO updateOut (Long id,RequestPerson requestPerson);

    PersonDTO deleteOut(Long id);

}
