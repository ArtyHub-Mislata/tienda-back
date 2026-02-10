package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import es.artyhub.tienda_back.persistence.TestConfig;
import es.artyhub.tienda_back.persistence.dao.jpa.SesionJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.SesionJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SesionJpaDaoImplTest {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private SesionJpaDao sesionJpaDao;
    
    
    @Nested
    @DisplayName("findByToken")
    public class FindByToken {
        @Test
        @DisplayName("should return session when token exists")
        public void shouldReturnSessionWhenTokenExists() {
            String token = "token1";
            
            Optional<SesionJpaEntity> result = sesionJpaDao.findByToken(token);
            
            assertEquals(token, result.get().getToken());
        }
        
        @Test
        @DisplayName("should return empty when token does not exist")
        public void shouldReturnEmptyWhenTokenDoesNotExist() {
            String token = "token";
            
            Optional<SesionJpaEntity> result = sesionJpaDao.findByToken(token);
            
            assertEquals(Optional.empty(), result);
        }
    }

    @Nested
    @DisplayName("deleteByToken")
    public class DeleteByToken {
        @Test
        @DisplayName("should delete session")
        public void shouldDeleteSession() {
            String token = "token1";
    
            sesionJpaDao.deleteSesion(token);
    
            Optional<SesionJpaEntity> result = sesionJpaDao.findByToken(token);
    
            assertEquals(Optional.empty(), result);
        }
    }
    
    @Nested
    @DisplayName("insert")
    public class Insert {
        @Test
        @DisplayName("should insert session")
        public void shouldInsertSession() {
            String token = "token";
            SesionJpaEntity sesionJpaEntity = new SesionJpaEntity(token, 1L, new Date());

            long countBefore = entityManager.createQuery("SELECT COUNT(s) FROM SesionJpaEntity s", Long.class)
            .getSingleResult();

            SesionJpaEntity result = sesionJpaDao.insertSesion(sesionJpaEntity);
            entityManager.flush();

            long countAfter = entityManager.createQuery("SELECT COUNT(s) FROM SesionJpaEntity s", Long.class)
            .getSingleResult();
           
            assertEquals(countBefore + 1, countAfter);
            assertEquals(sesionJpaEntity, result);
        }
    }

    @Nested
    @DisplayName("findUserByToken")
    public class FindUserByToken {
        @Test
        @DisplayName("should return session when token exists")
        public void shouldReturnSessionWhenTokenExists() {
            String token = "token1";

            Optional<SesionJpaEntity> result = sesionJpaDao.findByToken(token);
            
            assertEquals(1, result.get().getUserId());
        }
    }
}
