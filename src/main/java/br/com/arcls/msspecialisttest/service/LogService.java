package br.com.arcls.msspecialisttest.service;

import br.com.arcls.msspecialisttest.domain.dto.CardFlagEnum;
import br.com.arcls.msspecialisttest.domain.dto.LogRequest;
import br.com.arcls.msspecialisttest.domain.mapper.LogCreateMapper;
import br.com.arcls.msspecialisttest.domain.model.Log;
import br.com.arcls.msspecialisttest.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;
    private final LogCreateMapper logCreateMapper;

    @Async
    @Transactional
    public void create(LogRequest logRequest) {
        validation(logRequest);
        final Log log = logCreateMapper.toLog(logRequest);
        logRepository.save(log);
    }

    public Optional<List<Log>> findByFlagAndBetweenDates(CardFlagEnum flag, LocalDateTime from, LocalDateTime to){
        return logRepository.findByFlagAndBetweenDates(flag, from, to);
    }

    private void validation(LogRequest logRequest) throws ValidationException{
        if (!logRequest.getClient().toLowerCase(Locale.ROOT).startsWith("client")) {
            throw new ValidationException("Client need start with 'client'!");
        }
    }
}
