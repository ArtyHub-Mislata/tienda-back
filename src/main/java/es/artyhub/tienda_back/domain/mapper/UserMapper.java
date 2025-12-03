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
            userDto.id(),
            userDto.name(),
            userDto.email(),
            userDto.password(),
            userDto.nAccount(),
            userDto.description(),
            userDto.address(),
            userDto.imageProfileUrl(),
            userDto.artworks().stream().map(ArtworkMapper.getInstance()::fromArtworkDtoToArtwork).toList());
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
            user.getArtworks().stream().map(ArtworkMapper.getInstance()::fromArtworkToArtworkDto).toList());
    }
}
