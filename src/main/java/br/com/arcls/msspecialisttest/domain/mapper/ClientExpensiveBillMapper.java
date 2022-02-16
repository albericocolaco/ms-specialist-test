package br.com.arcls.msspecialisttest.domain.mapper;

import br.com.arcls.msspecialisttest.domain.dto.ClientExpensiveBillResponse;
import br.com.arcls.msspecialisttest.domain.model.Log;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ObjectIdMapper.class})
public abstract class ClientExpensiveBillMapper {

    public static ClientExpensiveBillResponse toExpensiveBillResponse(Log log) {
        final ClientExpensiveBillResponse response = new ClientExpensiveBillResponse();
        response.setClient(log.getClient());
        response.setFlag(log.getFlag().toString());
        response.setTotalValue(log.getValue());
        return response;
    }
}
