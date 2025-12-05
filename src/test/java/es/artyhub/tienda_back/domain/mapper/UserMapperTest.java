package es.artyhub.tienda_back.domain.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.User;

class UserMapperTest {
    
    @Nested
    @DisplayName("From User to UserDto")
    class FromUserToUserDto {
        
        @Test
        @DisplayName("Should return UserDto")
        void shouldReturnUserDto() {
            User user = new User(1L, "User", "user@email.com", "password", "12345678901234567890", "Description", "Address", "Image", new ArrayList<>());
            
            UserDto userDto = UserMapper.getInstance().fromUserToUserDto(user);
            
            assertAll(
                () -> assertNotNull(userDto, "UserDto should not be null"),
                () -> assertEquals(userDto.id(), user.getId(), "UserDto id should be equal to User id"),
                () -> assertEquals(userDto.name(), user.getName(), "UserDto name should be equal to User name"),
                () -> assertEquals(userDto.email(), user.getEmail(), "UserDto email should be equal to User email"),
                () -> assertEquals(userDto.password(), user.getPassword(), "UserDto password should be equal to User password"),
                () -> assertEquals(userDto.nAccount(), user.getnAccount(), "UserDto nAccount should be equal to User nAccount"),
                () -> assertEquals(userDto.description(), user.getDescription(), "UserDto description should be equal to User description"),
                () -> assertEquals(userDto.address(), user.getAddress(), "UserDto address should be equal to User address"),
                () -> assertEquals(userDto.imageProfileUrl(), user.getImageProfileUrl(), "UserDto imageProfileUrl should be equal to User imageProfileUrl"),
                () -> assertEquals(userDto.artworks(), user.getArtworks(), "UserDto artworks should be equal to User artworks")
            );
        }

        @Test
        @DisplayName("When user is null should throw BusinessException")
        void whenUserIsNull_ShouldThrowBusinessException() {
            User user = null;
            
            assertThrows(BusinessException.class, () -> UserMapper.getInstance().fromUserToUserDto(user));
        }
    }

    @Nested
    @DisplayName("From UserDto to User")
    class FromUserDtoToUser {
        
        @Test
        @DisplayName("Should return User")
        void shouldReturnUser() {
            UserDto userDto = new UserDto(1L, "User", "user@email.com", "password", "12345678901234567890", "Description", "Address", "Image", new ArrayList<>());
            
            User user = UserMapper.getInstance().fromUserDtoToUser(userDto);
            
            assertAll(
                () -> assertNotNull(user, "User should not be null"),
                () -> assertEquals(userDto.id(), user.getId(), "UserDto id should be equal to User id"),
                () -> assertEquals(userDto.name(), user.getName(), "UserDto name should be equal to User name"),
                () -> assertEquals(userDto.email(), user.getEmail(), "UserDto email should be equal to User email"),
                () -> assertEquals(userDto.password(), user.getPassword(), "UserDto password should be equal to User password"),
                () -> assertEquals(userDto.nAccount(), user.getnAccount(), "UserDto nAccount should be equal to User nAccount"),
                () -> assertEquals(userDto.description(), user.getDescription(), "UserDto description should be equal to User description"),
                () -> assertEquals(userDto.address(), user.getAddress(), "UserDto address should be equal to User address"),
                () -> assertEquals(userDto.imageProfileUrl(), user.getImageProfileUrl(), "UserDto imageProfileUrl should be equal to User imageProfileUrl"),
                () -> assertEquals(userDto.artworks(), user.getArtworks(), "UserDto artworks should be equal to User artworks")
            );
        }

        @Test
        @DisplayName("When userDto is null should throw BusinessException")
        void whenUserDtoIsNull_ShouldThrowBusinessException() {
            UserDto userDto = null;
            
            assertThrows(BusinessException.class, () -> UserMapper.getInstance().fromUserDtoToUser(userDto));
        }
    }
}
