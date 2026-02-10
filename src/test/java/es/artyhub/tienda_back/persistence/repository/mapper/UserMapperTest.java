package es.artyhub.tienda_back.persistence.repository.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;

public class UserMapperTest {
    
    @Nested
    @DisplayName("Test fromUserJpaEntityToUserDto")
    class FromUserJpaEntityToUserDtoTest {

        @Test
        @DisplayName("Test fromUserJpaEntityToUserDto with null UserJpaEntity should return null")
        void testFromUserJpaEntityToUserDto_NullInput() {
            UserDto result = UserMapper.getInstance().fromUserJpaEntityToUserDto(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromUserJpaEntityToUserDto with valid UserJpaEntity should return UserDto")
        void testFromUserJpaEntityToUserDto_ValidInput() {
            UserJpaEntity userJpaEntity = new UserJpaEntity(
                    1L,
                    "User",
                    "Email",
                    "Password",
                    "Description",
                    "Address",
                    "Image",
                    UserRole.USER
            );

            UserDto userDto = UserMapper.getInstance().fromUserJpaEntityToUserDto(userJpaEntity);

            assertAll(
                    () -> assertNotNull(userDto),
                    () -> assertEquals(1L, userDto.getId()),
                    () -> assertEquals("User", userDto.getName()),
                    () -> assertEquals("Description", userDto.getDescription()),
                    () -> assertEquals("Image", userDto.getImageProfileUrl()),
                    () -> assertEquals("Email", userDto.getEmail()),
                    () -> assertEquals("Password", userDto.getPassword()),
                    () -> assertEquals("Address", userDto.getAddress()),
                    () -> assertEquals(UserRole.USER, userDto.getRole()));
        }
    }

    @Nested
    @DisplayName("Test fromUserDtoToUserJpaEntity")
    class FromUserDtoToUserJpaEntityTest {

        @Test
        @DisplayName("Test fromUserDtoToUserJpaEntity with null UserDto should return null")
        void testFromUserDtoToUserJpaEntity_NullInput() {
            UserJpaEntity result = UserMapper.getInstance().fromUserDtoToUserJpaEntity(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromUserDtoToUserJpaEntity with valid UserDto should return UserJpaEntity")
        void testFromUserDtoToUserJpaEntity_ValidInput() {
            UserDto userDto = new UserDto(
                    1L,
                    "User",
                    "Email",
                    "Password",
                    "Description",
                    "Address",
                    "Image",
                    UserRole.USER
            );

            UserJpaEntity userJpaEntity = UserMapper.getInstance().fromUserDtoToUserJpaEntity(userDto);

            assertAll(
                    () -> assertNotNull(userJpaEntity),
                    () -> assertEquals(1L, userJpaEntity.getId()),
                    () -> assertEquals("User", userJpaEntity.getName()),
                    () -> assertEquals("Description", userJpaEntity.getDescription()),
                    () -> assertEquals("Image", userJpaEntity.getImageProfileUrl()),
                    () -> assertEquals("Email", userJpaEntity.getEmail()),
                    () -> assertEquals("Password", userJpaEntity.getPassword()),
                    () -> assertEquals("Address", userJpaEntity.getAddress()),
                    () -> assertEquals(UserRole.USER, userJpaEntity.getRole()));
        }
    }
}
