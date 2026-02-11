package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.exception.ResourceNotFoundException;
import es.artyhub.tienda_back.persistence.TestConfig;
import es.artyhub.tienda_back.persistence.dao.jpa.UserJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserJpaDaoImplTest {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private UserJpaDao userJpaDao;
    

    @Nested
    @DisplayName("findAll")
    public class FindAll {
        @Test
        @DisplayName("should return all users")
        public void shouldReturnAllUsers() {
            int page = 1;
            int size = 10;

            List<UserJpaEntity> result = userJpaDao.findAll(page, size);

            assertEquals(3, result.size());
        }
    }

    @Nested
    @DisplayName("findById")
    public class FindById {
        @Test
        @DisplayName("should return user when id exists")
        public void shouldReturnUserWhenIdExists() {
            Long userId = 1L;

            Optional<UserJpaEntity> result = userJpaDao.findById(userId);

            assertEquals(userId, result.get().getId());
        }

        @Test
        @DisplayName("should return empty when id does not exist")
        public void shouldReturnEmptyWhenIdDoesNotExist() {
            Long userId = 20L;

            Optional<UserJpaEntity> result = userJpaDao.findById(userId);

            assertEquals(Optional.empty(), result);
        }
    }

    @Nested
    @DisplayName("insert")
    public class Insert {
        @Test
        @DisplayName("should insert user")
        public void shouldInsertUser() {

            UserJpaEntity userJpaEntity = new UserJpaEntity(null, "User 1", "user1@example.com", "password", "description", "address", "image", UserRole.USER);
            
            long countBefore = entityManager.createQuery("SELECT COUNT(u) FROM UserJpaEntity u", Long.class)
            .getSingleResult();

            UserJpaEntity result = userJpaDao.insert(userJpaEntity);
            entityManager.flush();

            long countAfter = entityManager.createQuery("SELECT COUNT(u) FROM UserJpaEntity u", Long.class)
            .getSingleResult();

            assertEquals(countBefore + 1, countAfter);
            assertEquals(userJpaEntity, result);
        }
    }

    @Nested
    @DisplayName("update")
    public class Update {
        @Test
        @DisplayName("should update user")
        public void shouldUpdateUser() {
            Long userId = 1L;
            
            UserJpaEntity newUser = new UserJpaEntity(userId, "New User", "newuser@example.com", "newpassword", "newdescription", "newaddress", "newimage", UserRole.USER);
            entityManager.merge(newUser);
            entityManager.flush();

            Optional<UserJpaEntity> result = userJpaDao.findById(newUser.getId());

            assertEquals(newUser, result.get());
        }

        @Test
        @DisplayName("should throw resource not found exception when user does not exist")
        public void shouldNotUpdateUser() {
            Long userId = 30L;
            UserJpaEntity newUser = new UserJpaEntity(userId, "New User", "newuser@example.com", "newpassword", "newdescription", "newaddress", "newimage", UserRole.USER);

            assertThrows(ResourceNotFoundException.class, () -> userJpaDao.update(newUser));
        }
    }

    @Nested
    @DisplayName("deleteById")
    public class DeleteById {
        @Test
        @DisplayName("should delete user")
        public void shouldDeleteUser() {
            Long userId = 1L;

            userJpaDao.deleteById(userId);

            Optional<UserJpaEntity> result = userJpaDao.findById(userId);

            assertEquals(Optional.empty(), result);
        }
    }

    @Nested
    @DisplayName("count")
    public class Count {
        @Test
        @DisplayName("should return count of users")
        public void shouldReturnCountOfUsers() {
            Long count = userJpaDao.count();

            assertEquals(3, count);
        }
    }

    @Nested
    @DisplayName("findByEmail")
    public class FindByEmail {
        @Test
        @DisplayName("should return user when email exists")
        public void shouldReturnUserWhenEmailExists() {
            String email = "ana@artyhub.com";

            UserJpaEntity result = userJpaDao.findByEmail(email);

            assertEquals(email, result.getEmail());
        }
    }
}
