package br.com.arcls.msspecialisttest.repository;

import br.com.arcls.msspecialisttest.domain.dto.CardFlagEnum;
import br.com.arcls.msspecialisttest.domain.model.Log;
import br.com.arcls.msspecialisttest.domain.model.User;
import org.bson.types.ObjectId;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@CacheConfig(cacheNames = "logs")
public interface LogRepository extends LogRepositoryCustom, MongoRepository<Log, ObjectId> {

    <S extends Log> S save(S entity);

    Optional<List<Log>> findByFlag(CardFlagEnum flag);
}
