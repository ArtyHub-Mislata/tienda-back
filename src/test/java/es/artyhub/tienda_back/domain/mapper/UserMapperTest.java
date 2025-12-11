package es.artyhub.tienda_back.domain.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.User;

class UserMapperTest {
    
    @Nested
    @DisplayName("From User to UserDto")
    class FromUserToUserDto {
        
        @Test
        @DisplayName("Should return UserDto")
        void shouldReturnUserDto() {
            User user = new User(1L, "User", "user@email.com", "password", "1234567890123456", "Description", "Address", "Image", UserRole.ADMIN);
            
            UserDto userDto = UserMapper.getInstance().fromUserToUserDto(user);
            
            assertAll(
                () -> assertNotNull(userDto, "UserDto should not be null"),
                () -> assertEquals(userDto.getId(), user.getId(), "UserDto id should be equal to User id"),
                () -> assertEquals(userDto.getName(), user.getName(), "UserDto name should be equal to User name"),
                () -> assertEquals(userDto.getEmail(), user.getEmail(), "UserDto email should be equal to User email"),
                () -> assertEquals(userDto.getPassword(), user.getPassword(), "UserDto password should be equal to User password"),
                () -> assertEquals(userDto.getnAccount(), user.getnAccount(), "UserDto nAccount should be equal to User nAccount"),
                () -> assertEquals(userDto.getDescription(), user.getDescription(), "UserDto description should be equal to User description"),
                () -> assertEquals(userDto.getAddress(), user.getAddress(), "UserDto address should be equal to User address"),
                () -> assertEquals(userDto.getImageProfileUrl(), user.getImageProfileUrl(), "UserDto imageProfileUrl should be equal to User imageProfileUrl"),
                () -> assertEquals(userDto.getRole(), user.getRole(), "UserDto role should be equal to User role")
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
            UserDto userDto = new UserDto(1L, "User", "user@email.com", "password", "1234567890123456", "Description", "Address", "Image", UserRole.ADMIN);
            
            User user = UserMapper.getInstance().fromUserDtoToUser(userDto);
            
            assertAll(
                () -> assertNotNull(user, "User should not be null"),
                () -> assertEquals(userDto.getId(), user.getId(), "UserDto id should be equal to User id"),
                () -> assertEquals(userDto.getName(), user.getName(), "UserDto name should be equal to User name"),
                () -> assertEquals(userDto.getEmail(), user.getEmail(), "UserDto email should be equal to User email"),
                () -> assertEquals(userDto.getPassword(), user.getPassword(), "UserDto password should be equal to User password"),
                () -> assertEquals(userDto.getnAccount(), user.getnAccount(), "UserDto nAccount should be equal to User nAccount"),
                () -> assertEquals(userDto.getDescription(), user.getDescription(), "UserDto description should be equal to User description"),
                () -> assertEquals(userDto.getAddress(), user.getAddress(), "UserDto address should be equal to User address"),
                () -> assertEquals(userDto.getImageProfileUrl(), user.getImageProfileUrl(), "UserDto imageProfileUrl should be equal to User imageProfileUrl"),
                () -> assertEquals(userDto.getRole(), user.getRole(), "UserDto role should be equal to User role")
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
