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

public class UserMapperTest {
    
    @Nested
    @DisplayName("Test fromUserToUserDto")
    class FromUserToUserDtoTest {

        @Test
        @DisplayName("Test fromUserToUserDto with null User should throw exception")
        void testFromUserToUserDto_NullInput() {
            assertThrows(BusinessException.class, () -> UserMapper.getInstance().fromUserToUserDto(null));
        }

        @Test
        @DisplayName("Test fromUserToUserDto with valid User should return UserDto")
        void testFromUserToUserDto_ValidInput() {
            User user = new User(
                    1L,
                    "User",
                    "email@gmail.com",
                    "Password",
                    "Description",
                    "Address",
                    "Image",
                    UserRole.USER
            );

            UserDto userDto = UserMapper.getInstance().fromUserToUserDto(user);

            assertAll(
                    () -> assertNotNull(userDto),
                    () -> assertEquals(1L, userDto.getId()),
                    () -> assertEquals("User", userDto.getName()),
                    () -> assertEquals("Description", userDto.getDescription()),
                    () -> assertEquals(UserRole.USER, userDto.getRole()));
        }
    }

    @Nested
    @DisplayName("Test fromUserDtoToUser")
    class FromUserDtoToUserTest {

        @Test
        @DisplayName("Test fromUserDtoToUser with null UserDto should throw exception")
        void testFromUserDtoToUser_NullInput() {
            assertThrows(BusinessException.class, () -> UserMapper.getInstance().fromUserDtoToUser(null));
        }

        @Test
        @DisplayName("Test fromUserDtoToUser with valid UserDto should return User")
        void testFromUserDtoToUser_ValidInput() {
            UserDto userDto = new UserDto(
                    1L,
                    "User",
                    "email@gmail.com",
                    "Password",
                    "Description",
                    "Address",
                    "Image",
                    UserRole.USER
            );

            User user = UserMapper.getInstance().fromUserDtoToUser(userDto);

            assertAll(
                    () -> assertNotNull(user),
                    () -> assertEquals(1L, user.getId()),
                    () -> assertEquals("User", user.getName()),
                    () -> assertEquals("Description", user.getDescription()),
                    () -> assertEquals(UserRole.USER, user.getRole()));
        }
    }
}
