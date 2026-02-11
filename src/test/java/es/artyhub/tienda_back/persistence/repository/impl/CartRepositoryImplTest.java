package es.artyhub.tienda_back.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.tienda_back.domain.dto.CartDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.persistence.dao.jpa.CartJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CartJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;

import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
public class CartRepositoryImplTest {
    
    @Mock
    private CartJpaDao cartJpaDao;

    @InjectMocks
    private CartRepositoryImpl cartRepository;

    @Nested
    @DisplayName("getCartOfUser")
    class GetCartOfUserTest {
        
        @Test
        @DisplayName("Should return a cart")
        void shouldReturnCart() {
            Long userId = 1L;
            UserJpaEntity userJpaEntity = new UserJpaEntity(userId, "User", "Email", "Password", "description", "address", "image", UserRole.ADMIN);
            CartJpaEntity cartJpaEntity = new CartJpaEntity(1L, List.of(), userJpaEntity);

            when(cartJpaDao.getCartOfUser(userId)).thenReturn(cartJpaEntity);

            CartDto cartDto = cartRepository.getCartOfUser(userId);

            assertEquals(cartJpaEntity.getId(), cartDto.getId());
        }
    }
}
