package com.codigo.msregistro.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {
    private Long idPersona;

    private String numDocu;

    private String nombres;

    private String apePat;

    private String apeMat;

    private Integer estado;

    private String usuaCrea;

    private Timestamp dateCreate;
    //SI corre pero cuando quiero crear no guarda
    private String usuaModif;

    private Timestamp dateModif;

    private String usuaDelet;

    private Timestamp dateDelete;
}
