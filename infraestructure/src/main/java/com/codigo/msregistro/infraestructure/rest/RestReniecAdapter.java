package com.codigo.msregistro.infraestructure.rest;

import com.codigo.msregistro.domain.aggregates.response.ReniecResponse;
import com.codigo.msregistro.domain.ports.out.RestReniecOut;
import com.codigo.msregistro.infraestructure.rest.client.ClientReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestReniecAdapter implements RestReniecOut {

    private final ClientReniec reniec;

    @Value("${token.api}")
    private String tokenApi;

    //En su proyecto si corre, pero aqui no xddd y aqui esta igual
    @Override
    public ReniecResponse getInfoReniec(String numDoc) {
        String authorization = "Bearer "+tokenApi;
        ReniecResponse reniecResponse = reniec.getInfoReniec(numDoc,authorization);
        return reniecResponse;
    }

}
