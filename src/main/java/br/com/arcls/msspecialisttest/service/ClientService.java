package br.com.arcls.msspecialisttest.service;

import br.com.arcls.msspecialisttest.domain.dto.CardFlagEnum;
import br.com.arcls.msspecialisttest.domain.dto.ClientExpensiveBillResponse;
import br.com.arcls.msspecialisttest.domain.mapper.ClientExpensiveBillMapper;
import br.com.arcls.msspecialisttest.domain.model.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final LogService logService;

    public List<ClientExpensiveBillResponse> getLastExpensiveBillsFromFlag(){
        final List<ClientExpensiveBillResponse> result = new ArrayList<>();
        final LocalDateTime to = LocalDateTime.now();
        final LocalDateTime from = to.minusMinutes(30);

        for (CardFlagEnum flag : CardFlagEnum.values()) {
            logService.findByFlagAndBetweenDates(flag, from, to).flatMap(logs -> logs
                    .stream()
                    .max(Comparator.comparing(Log::getValue))
                    .map(ClientExpensiveBillMapper::toExpensiveBillResponse)).ifPresent(result::add);
        }

        return result;
    }
}
