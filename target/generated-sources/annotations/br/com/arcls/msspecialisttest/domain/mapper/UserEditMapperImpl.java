package br.com.arcls.msspecialisttest.domain.mapper;

import br.com.arcls.msspecialisttest.domain.dto.CreateUserRequest;
import br.com.arcls.msspecialisttest.domain.dto.UpdateUserRequest;
import br.com.arcls.msspecialisttest.domain.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-16T14:20:41-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
@Component
public class UserEditMapperImpl extends UserEditMapper {

    @Override
    public User create(CreateUserRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( request.getUsername() );
        user.setPassword( request.getPassword() );
        user.setFullName( request.getFullName() );

        afterCreate( request, user );

        return user;
    }

    @Override
    public void update(UpdateUserRequest request, User user) {
        if ( request == null ) {
            return;
        }

        if ( request.getFullName() != null ) {
            user.setFullName( request.getFullName() );
        }

        afterUpdate( request, user );
    }
}
