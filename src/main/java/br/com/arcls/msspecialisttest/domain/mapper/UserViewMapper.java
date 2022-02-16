package br.com.arcls.msspecialisttest.domain.mapper;

import br.com.arcls.msspecialisttest.domain.dto.UserView;
import br.com.arcls.msspecialisttest.domain.exception.NotFoundException;
import br.com.arcls.msspecialisttest.domain.model.User;
import br.com.arcls.msspecialisttest.repository.UserRepository;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", uses = {ObjectIdMapper.class})
public abstract class UserViewMapper {

    @Autowired
    private UserRepository userRepository;

    public abstract UserView toUserView(User user);

    public abstract List<UserView> toUserView(List<User> users);

    public UserView toUserViewById(ObjectId id) {
        if (id == null) {
            return null;
        }
        final Optional<User> user = userRepository.findById(id);
        if (user.isPresent() && user.get().isEnabled()) {
            return toUserView(user.get());
        } else {
            throw new NotFoundException(User.class, id);
        }
    }

}