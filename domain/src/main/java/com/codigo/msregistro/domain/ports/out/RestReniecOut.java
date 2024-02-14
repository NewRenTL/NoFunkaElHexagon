package com.codigo.msregistro.domain.ports.out;

import com.codigo.msregistro.domain.aggregates.response.ReniecResponse;

public interface RestReniecOut {
    ReniecResponse getInfoReniec(String numDoc);
}
