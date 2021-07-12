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
    date = "2021-07-13T00:48:10+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public User mapToUser(CreateUserRequest createUserRequest, Role role, String encodePassword) {
        if ( createUserRequest == null && role == null && encodePassword == null ) {
            return null;
        }

        User user = new User();

        if ( createUserRequest != null ) {
            user.setUsername( createUserRequest.getUsername() );
        }
        if ( role != null ) {
            user.setRoles( role );
        }
        if ( encodePassword != null ) {
            user.setPassword( encodePassword );
        }
        user.setStatus( 1 );

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
            getUserResponse.setStatus( user.getStatus() );
        }
        if ( roleName != null ) {
            getUserResponse.setRoleName( roleName );
        }

        return getUserResponse;
    }
}
