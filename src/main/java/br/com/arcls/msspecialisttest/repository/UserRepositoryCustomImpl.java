package br.com.arcls.msspecialisttest.repository;

import br.com.arcls.msspecialisttest.domain.dto.Page;
import br.com.arcls.msspecialisttest.domain.dto.SearchUsersQuery;
import br.com.arcls.msspecialisttest.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.skip;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<User> searchUsers(Page page, SearchUsersQuery query) {
        List<AggregationOperation> operations = new ArrayList<>();

        List<Criteria> criteriaList = new ArrayList<>();
        if (!StringUtils.isEmpty(query.getId())) {
            criteriaList.add(Criteria.where("id").is(new ObjectId(query.getId())));
        }
        if (!StringUtils.isEmpty(query.getUsername())) {
            criteriaList.add(Criteria.where("username").regex(query.getUsername(), "i"));
        }
        if (!StringUtils.isEmpty(query.getFullName())) {
            criteriaList.add(Criteria.where("fullName").regex(query.getFullName(), "i"));
        }
        if (!criteriaList.isEmpty()) {
            Criteria userCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
            operations.add(match(userCriteria));
        }

        operations.add(sort(Sort.Direction.DESC, "createdAt"));
        operations.add(skip((page.getNumber() - 1) * page.getLimit()));
        operations.add(limit(page.getLimit()));

        TypedAggregation<User> aggregation = newAggregation(User.class, operations);
        AggregationResults<User> results = mongoTemplate.aggregate(aggregation, User.class);
        return results.getMappedResults();
    }
}
