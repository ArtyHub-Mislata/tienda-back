package es.artyhub.tienda_back.domain.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.tienda_back.domain.dto.CartDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.repository.CartRepository;
import es.artyhub.tienda_back.domain.service.impl.CartServiceImpl;

import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {
    
    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @Test
    @DisplayName("getCartOfUser_WhenUserExists")
    public void getCartOfUser_WhenUserExists() {
        Long userId = 1L;
        UserDto userDto = new UserDto(userId, "manolo", "email", "password", "description", "address", "image", UserRole.USER);
        CartDto cartDto = new CartDto(1L, List.of(), userDto);

        when(cartRepository.getCartOfUser(userId)).thenReturn(cartDto);

        CartDto result = cartService.getCartOfUser(userId);

        assertEquals(cartDto, result);
    }
}