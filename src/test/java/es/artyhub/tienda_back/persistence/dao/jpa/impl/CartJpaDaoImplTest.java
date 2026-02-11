package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;

import es.artyhub.tienda_back.persistence.TestConfig;
import es.artyhub.tienda_back.persistence.dao.jpa.CartJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CartJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartJpaDaoImplTest {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private CartJpaDao cartJpaDao;


    @Nested
    @DisplayName("getCartOfUser")
    public class GetCartOfUser {
        @Test
        @DisplayName("should return cart of user")
        public void shouldReturnCartOfUser() {
            Long userId = 1L;

            CartJpaEntity result = cartJpaDao.getCartOfUser(userId);

            assertEquals(userId, result.getUser().getId());
        }
    }
}
