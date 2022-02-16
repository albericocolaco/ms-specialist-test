package br.com.arcls.msspecialisttest.repository;

import br.com.arcls.msspecialisttest.domain.dto.CardFlagEnum;
import br.com.arcls.msspecialisttest.domain.model.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class LogRepositoryCustomImpl implements LogRepositoryCustom{

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<List<Log>> findByFlagAndBetweenDates(CardFlagEnum flag, LocalDateTime from, LocalDateTime to) {
        return Optional.of(
            mongoTemplate.find(getQuery(flag, from, to), Log.class)
        );
    }

    private Query getQuery(CardFlagEnum flag, LocalDateTime from, LocalDateTime to) {
        final List<Criteria> filters = getFilters(flag, from, to);
        final Criteria criteria = new Criteria();
        if (!filters.isEmpty()) {
            criteria.andOperator(filters.toArray(new Criteria[0]));
        }
        return Query.query(criteria);
    }

    private List<Criteria> getFilters(CardFlagEnum flag, LocalDateTime from, LocalDateTime to) {
        final List<Criteria> criteriaList = new ArrayList<>();
        if (!StringUtils.isEmpty(flag)) {
            criteriaList.add(Criteria.where("flag").is(flag.toString()));
        }
        if (from != null && to != null) {
            criteriaList.add(Criteria.where("createdAt").gte(from).lt(to));
        }
        return criteriaList;
    }
}
