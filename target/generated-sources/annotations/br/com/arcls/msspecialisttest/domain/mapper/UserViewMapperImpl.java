package br.com.arcls.msspecialisttest.domain.mapper;

import br.com.arcls.msspecialisttest.domain.dto.UserView;
import br.com.arcls.msspecialisttest.domain.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-16T14:20:41-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
@Component
public class UserViewMapperImpl extends UserViewMapper {

    @Autowired
    private ObjectIdMapper objectIdMapper;

    @Override
    public UserView toUserView(User user) {
        if ( user == null ) {
            return null;
        }

        UserView userView = new UserView();

        userView.setId( objectIdMapper.objectIdToString( user.getId() ) );
        userView.setUsername( user.getUsername() );
        userView.setFullName( user.getFullName() );
        userView.setCreatedAt( user.getCreatedAt() );
        userView.setModifiedAt( user.getModifiedAt() );

        return userView;
    }

    @Override
    public List<UserView> toUserView(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserView> list = new ArrayList<UserView>( users.size() );
        for ( User user : users ) {
            list.add( toUserView( user ) );
        }

        return list;
    }
}
