package com.codigo.msregistro.infraestructure.mapper;

import com.codigo.msregistro.domain.aggregates.dto.PersonDTO;
import com.codigo.msregistro.infraestructure.entity.PersonEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class PersonMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public PersonDTO mapToDto(PersonEntity entity)
    {
        return modelMapper.map(entity,PersonDTO.class);

    }

    public PersonEntity mapToEntity(PersonDTO personDTO)
    {
        return modelMapper.map(personDTO,PersonEntity.class);
    }
}
