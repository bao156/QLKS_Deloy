package qlks_hdv.mapstruct;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import qlks_hdv.entity.Roles;
import qlks_hdv.entity.User;
import qlks_hdv.request.CreateUserRequest;
import qlks_hdv.request.UpdateUserRequest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-20T15:40:26+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User mapBeforeCreate(CreateUserRequest createUserRequest, Roles role) {
        if ( createUserRequest == null && role == null ) {
            return null;
        }

        User user = new User();

        if ( createUserRequest != null ) {
            user.setUsername( createUserRequest.getUsername() );
            user.setPassword( createUserRequest.getPassword() );
        }
        if ( role != null ) {
            user.setRole( role );
        }

        return user;
    }

    @Override
    public User mapBeforeUpdate(UpdateUserRequest updateUserRequest, User user) {
        if ( updateUserRequest == null ) {
            return null;
        }

        user.setPassword( updateUserRequest.getPassword() );

        return user;
    }
}
