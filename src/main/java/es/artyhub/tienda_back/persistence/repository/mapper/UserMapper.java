package es.artyhub.tienda_back.persistence.repository.mapper;

import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;

import java.util.List;

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

    public UserDto fromUserJpaEntityToUserDto(UserJpaEntity userJpaEntity){
        if(userJpaEntity == null){
            return null;
        }

        return new UserDto(
                userJpaEntity.getId(),
                userJpaEntity.getName(),
                userJpaEntity.getEmail(),
                userJpaEntity.getPassword(),
                userJpaEntity.getnAccount(),
                userJpaEntity.getDescription(),
                userJpaEntity.getAddress(),
                userJpaEntity.getImageProfileUrl(),
                List.of(),
                userJpaEntity.getRole()
        );
    }
    public UserJpaEntity fromUserDtoToUserJpaEntity(UserDto userDto){
        if(userDto == null){
            return null;
        }

        return new UserJpaEntity(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getnAccount(),
                userDto.getDescription(),
                userDto.getAddress(),
                userDto.getImageProfileUrl(),
                userDto.getRol()
        );
    }
}
