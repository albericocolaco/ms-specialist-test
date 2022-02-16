package br.com.arcls.msspecialisttest.domain.mapper;

import br.com.arcls.msspecialisttest.domain.dto.CardFlagEnum;
import br.com.arcls.msspecialisttest.domain.dto.LogRequest;
import br.com.arcls.msspecialisttest.domain.model.Log;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ObjectIdMapper.class})
public abstract class LogCreateMapper {

    public Log toLog(LogRequest request) {
        if ( request == null ) {
            return null;
        }

        final Log log = new Log();
        log.setClient(request.getClient());
        log.setValue(request.getValue());
        log.setTransactionAt(request.getTransactionAt());
        log.setFlag(CardFlagEnum.fromString(request.getFlag()));

        return log;
    }
}
