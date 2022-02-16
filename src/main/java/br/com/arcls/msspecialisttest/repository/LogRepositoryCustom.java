package br.com.arcls.msspecialisttest.repository;

import br.com.arcls.msspecialisttest.domain.dto.CardFlagEnum;
import br.com.arcls.msspecialisttest.domain.model.Log;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LogRepositoryCustom {
    Optional<List<Log>> findByFlagAndBetweenDates(CardFlagEnum flag, LocalDateTime from, LocalDateTime to);
}
