package es.artyhub.tienda_back.controller.mapper;

import es.artyhub.tienda_back.controller.webmodel.response.UserSummaryResponse;
import es.artyhub.tienda_back.domain.dto.UserDto;
import org.junit.jupiter.api.Test;
import es.artyhub.tienda_back.domain.enums.UserRole;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

public class UserMapperTest {

    @Test
    @DisplayName("Test fromUserDtoToUserSummaryResponse with null UserDto should return null")
    void testFromUserDtoToUserSummaryResponse_NullInput() {
        UserSummaryResponse result = UserMapper.getInstance().fromUserDtoToUserSummaryResponse(null);
        assertNull(result);
    }

    @Test
    @DisplayName("Test fromUserDtoToUserSummaryResponse with valid UserDto should return UserSummaryResponse")
    void testFromUserDtoToUserSummaryResponse_ValidInput() {
        UserDto userDto = new UserDto(
                1L,
                "John Doe",
                "[EMAIL_ADDRESS]",
                "password",
                "Software Engineer",
                "123 Main St",
                "https://example.com/avatar.jpg",
                UserRole.USER
        );

        UserSummaryResponse userSummaryResponse = UserMapper.getInstance().fromUserDtoToUserSummaryResponse(userDto);

        assertAll(
                () -> assertNotNull(userSummaryResponse),
                () -> assertEquals(1L, userSummaryResponse.id()),
                () -> assertEquals("John Doe", userSummaryResponse.name()),
                () -> assertEquals("[EMAIL_ADDRESS]", userSummaryResponse.email()),
                () -> assertEquals("Software Engineer", userSummaryResponse.description()),
                () -> assertEquals("123 Main St", userSummaryResponse.address()),
                () -> assertEquals("https://example.com/avatar.jpg", userSummaryResponse.imageProfileUrl()),
                () -> assertEquals(UserRole.USER, userSummaryResponse.role()));
    }
}