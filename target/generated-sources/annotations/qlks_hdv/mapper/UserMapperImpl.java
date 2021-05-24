package qlks_hdv.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import qlks_hdv.entity.Role;
import qlks_hdv.entity.User;
import qlks_hdv.request.CreateUserRequest;
import qlks_hdv.request.UpdateUserRequest;
import qlks_hdv.response.GetUserResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-24T13:13:45+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User mapToUser(CreateUserRequest createUserRequest, Role role) {
        if ( createUserRequest == null && role == null ) {
            return null;
        }

        User user = new User();

        if ( createUserRequest != null ) {
            user.setUsername( createUserRequest.getUsername() );
            user.setPassword( createUserRequest.getPassword() );
        }
        if ( role != null ) {
            user.setRoles( role );
        }

        return user;
    }

    @Override
    public User mapToUser(UpdateUserRequest updateUserRequest, User user) {
        if ( updateUserRequest == null ) {
            return null;
        }

        user.setPassword( updateUserRequest.getPassword() );

        return user;
    }

    @Override
    public GetUserResponse mapToGetUserResponse(User user, String roleName) {
        if ( user == null && roleName == null ) {
            return null;
        }

        GetUserResponse getUserResponse = new GetUserResponse();

        if ( user != null ) {
            getUserResponse.setUsername( user.getUsername() );
            getUserResponse.setPassword( user.getPassword() );
        }
        if ( roleName != null ) {
            getUserResponse.setRoleName( roleName );
        }

        return getUserResponse;
    }
}
