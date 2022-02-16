package br.com.arcls.msspecialisttest.fixture;

import br.com.arcls.msspecialisttest.domain.dto.CardFlagEnum;
import br.com.arcls.msspecialisttest.domain.model.Log;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LogFixture {

    public static Log getLog() {
        final LocalDateTime now = LocalDateTime.now();
        final Log log = new Log();
        log.setClient("client-teste");
        log.setTransactionAt(now);
        log.setValue(BigDecimal.valueOf(22.99));
        log.setCreatedAt(LocalDateTime.now());
        log.setFlag(CardFlagEnum.AMERICANEXPRESS);
        log.setId(new ObjectId());
        return log;
    }
}
