package br.com.arcls.msspecialisttest.repository;


import br.com.arcls.msspecialisttest.domain.exception.NotFoundException;
import br.com.arcls.msspecialisttest.domain.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends UserRepositoryCustom, MongoRepository<User, ObjectId> {

    Optional<User> findById(ObjectId objectId);

    Optional<User> findByUsername(String username);
}
