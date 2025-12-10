package es.artyhub.tienda_back.domain.mapper;

import es.artyhub.tienda_back.domain.model.User;
import es.artyhub.tienda_back.domain.dto.UserDto;

public class UserMapper {
    private static UserMapper instance;

    private UserMapper() {
    }

    public static UserMapper getInstance() {
        if (instance == null) {
            instance = new UserMapper();
        }
        return instance;
    }

    public User fromUserDtoToUser(UserDto userDto) {
        return new User(
            userDto.getId(),
            userDto.getName(),
            userDto.getEmail(),
            userDto.getPassword(),
            userDto.getnAccount(),
            userDto.getDescription(),
            userDto.getAddress(),
            userDto.getImageProfileUrl(),
            userDto.getRole()
        );

    }

    public UserDto fromUserToUserDto(User user) {
        return new UserDto(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getPassword(),
            user.getnAccount(),
            user.getDescription(),
            user.getAddress(),
            user.getImageProfileUrl(),
            user.getRole()
        );
    }
}
