package es.artyhub.tienda_back.controller.mapper;

import es.artyhub.tienda_back.controller.webmodel.response.UserSummaryResponse;
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

    public UserSummaryResponse fromUserDtoToUserSummaryResponse(UserDto userDto){
        if(userDto == null){
            return null;
        }
        return new UserSummaryResponse(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getDescription(),
                userDto.getAddress(),
                userDto.getImageProfileUrl(),
                userDto.getRole()
        );
    }
}
