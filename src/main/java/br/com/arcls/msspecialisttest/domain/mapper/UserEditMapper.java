package br.com.arcls.msspecialisttest.domain.mapper;

import br.com.arcls.msspecialisttest.domain.dto.CreateUserRequest;
import br.com.arcls.msspecialisttest.domain.dto.UpdateUserRequest;
import br.com.arcls.msspecialisttest.domain.model.Role;
import br.com.arcls.msspecialisttest.domain.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static java.util.stream.Collectors.toSet;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public abstract class UserEditMapper {

    @Mapping(target = "authorities", ignore = true)
    public abstract User create(CreateUserRequest request);

    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "authorities", ignore = true)
    public abstract void update(UpdateUserRequest request, @MappingTarget User user);

    @AfterMapping
    protected void afterCreate(CreateUserRequest request, @MappingTarget User user) {
        if (request.getAuthorities() != null) {
            user.setAuthorities(request.getAuthorities().stream().map(Role::new).collect(toSet()));
        }
    }

    @AfterMapping
    protected void afterUpdate(UpdateUserRequest request, @MappingTarget User user) {
        if (request.getAuthorities() != null) {
            user.setAuthorities(request.getAuthorities().stream().map(Role::new).collect(toSet()));
        }
    }

}
