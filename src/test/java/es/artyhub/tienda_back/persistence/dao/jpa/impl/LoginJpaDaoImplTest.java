package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import es.artyhub.tienda_back.domain.dto.CredentialsDto;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.persistence.TestConfig;
import es.artyhub.tienda_back.persistence.dao.jpa.LoginJpaDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LoginJpaDaoImplTest {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private LoginJpaDao loginJpaDao;


    @Nested
    @DisplayName("login")
    public class LoginTest {

        @Test
        @DisplayName("should login if credentials are correct")
        public void shouldLoginIfCredentialsAreCorrect() {
            CredentialsDto credentialsDto = new CredentialsDto("ana@artyhub.com", "pass");
    
            String result = loginJpaDao.login(credentialsDto);
    
            assertNotNull(result);
        }

        @Test
        @DisplayName("should throw validation exception if email is incorrect")
        public void shouldThrowValidationExceptionIfEmailIsIncorrect() {
            CredentialsDto credentialsDto = new CredentialsDto("", "pass");
    
            assertThrows(ValidationException.class, () -> loginJpaDao.login(credentialsDto));
        }

        @Test
        @DisplayName("should throw validation exception if email is invalid")
        public void shouldThrowValidationExceptionIfEmailIsInvalid() {
            CredentialsDto credentialsDto = new CredentialsDto("email", "pass");
    
            assertThrows(ValidationException.class, () -> loginJpaDao.login(credentialsDto));
        }

        @Test
        @DisplayName("should throw validation exception if password is incorrect")
        public void shouldThrowValidationExceptionIfPasswordIsIncorrect() {
            CredentialsDto credentialsDto = new CredentialsDto("ana@artyhub.com", "");
    
            assertThrows(ValidationException.class, () -> loginJpaDao.login(credentialsDto));
        }

        @Test
        @DisplayName("should throw validation exception if password is not the same")
        public void shouldThrowValidationExceptionIfPasswordIsNotTheSame() {
            CredentialsDto credentialsDto = new CredentialsDto("ana@artyhub.com", "Pass");

            assertThrows(ValidationException.class, () -> loginJpaDao.login(credentialsDto));
        }
    }
}
