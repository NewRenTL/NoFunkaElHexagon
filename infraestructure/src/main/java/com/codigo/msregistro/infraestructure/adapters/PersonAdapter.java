package com.codigo.msregistro.infraestructure.adapters;

import com.codigo.msregistro.domain.aggregates.constants.Constants;
import com.codigo.msregistro.domain.aggregates.dto.PersonDTO;
import com.codigo.msregistro.domain.aggregates.request.RequestPerson;
import com.codigo.msregistro.domain.aggregates.response.ReniecResponse;
import com.codigo.msregistro.domain.ports.out.PersonServiceOut;
import com.codigo.msregistro.infraestructure.entity.DocumentTypeEntity;
import com.codigo.msregistro.infraestructure.entity.PersonEntity;
import com.codigo.msregistro.infraestructure.mapper.PersonMapper;
import com.codigo.msregistro.infraestructure.repository.DocumentTypeRepository;
import com.codigo.msregistro.infraestructure.repository.PersonRepository;
import com.codigo.msregistro.infraestructure.rest.client.ClientReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PersonAdapter implements PersonServiceOut {

    private final PersonRepository personRepository;

    private final DocumentTypeRepository documentTypeRepository;

    private final PersonMapper personMapper;

    private final ClientReniec clientReniec;

    @Value("token.api")
    private String tokenApi;

    @Override
    public PersonDTO createPersonOut(RequestPerson requestPerson) {
        ReniecResponse datosReniec = getExecutionReniec(requestPerson.getNumDoc());
        personRepository.save(getEntity(datosReniec,requestPerson));
        return personMapper.mapToDto(getEntity(datosReniec,requestPerson));
    }

    @Override
    public Optional<PersonDTO> getPersonOut(Long id) {

        return Optional.ofNullable(personMapper.mapToDto(personRepository.findById(id).get()));
    }

    @Override
    public List<PersonDTO> getAllOut() {
        List<PersonDTO> personDTOList = new ArrayList<>();
        List<PersonEntity> entities = personRepository.findAll();
        for (PersonEntity person:entities)
        {
            PersonDTO personDTO = personMapper.mapToDto(person);
            personDTOList.add(personDTO);
        }
        return personDTOList;
    }

    @Override
    public PersonDTO updateOut(Long id, RequestPerson requestPerson) {
        boolean exists = personRepository.existsById(id);
        if(exists)
        {
            Optional<PersonEntity> entity = personRepository.findById(id);
            ReniecResponse reniecResponse = getExecutionReniec(requestPerson.getNumDoc());
            personRepository.save(getEntityUpdate(reniecResponse,entity.get()));
            return personMapper.mapToDto(getEntityUpdate(reniecResponse,entity.get()));
        }
        return null;
    }

    @Override
    public PersonDTO deleteOut(Long id) {
        boolean exists = personRepository.existsById(id);
        if(exists)
        {
            Optional<PersonEntity> entity = personRepository.findById(id);
            entity.get().setEstado(0);
            entity.get().setUsuaDelet(Constants.AUDIT_ADMIN);
            entity.get().setDateDelet(getTimestamp());
            personRepository.save(entity.get());
            return personMapper.mapToDto(entity.get());
        }
        return null;
    }


    public ReniecResponse getExecutionReniec(String numero){
        String authorization = "Bearer "+tokenApi;
        ReniecResponse responseReniec = clientReniec.getInfoReniec(numero,authorization);
        return  responseReniec;
        // como?
    }
    private PersonEntity getEntity(ReniecResponse clientReniec, RequestPerson requestPersona){
        DocumentTypeEntity tipoDocumento = documentTypeRepository.findByCodTipo(requestPersona.getTipoDoc());
        PersonEntity entity = new PersonEntity();
        entity.setNumDocu(clientReniec.getNumeroDocumento());
        entity.setNombres(clientReniec.getNombres());
        entity.setApePat(clientReniec.getApellidoPaterno());
        entity.setApeMat(clientReniec.getApellidoMaterno());
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimestamp());
        entity.setTipoDocumento(tipoDocumento);
        return entity;
    }
    private PersonEntity getEntityUpdate(ReniecResponse reniec, PersonEntity personaActualizar){
        personaActualizar.setNombres(reniec.getNombres());
        personaActualizar.setApePat(reniec.getApellidoPaterno());
        personaActualizar.setApeMat(reniec.getApellidoMaterno());
        personaActualizar.setUsuaModif(Constants.AUDIT_ADMIN);
        personaActualizar.setDateModif(getTimestamp());
        return personaActualizar;
    }
    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}
