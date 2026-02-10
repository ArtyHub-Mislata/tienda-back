package es.artyhub.tienda_back.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.tienda_back.domain.dto.CredentialsDto;
import es.artyhub.tienda_back.persistence.dao.jpa.LoginJpaDao;

import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
public class LoginRepositoryImplTest {
    
    @Mock
    private LoginJpaDao loginJpaDao;

    @InjectMocks
    private LoginRepositoryImpl loginRepository;

    @Nested
    @DisplayName("Login Tests")
    class LoginTest {
        
        @Test
        @DisplayName("Should login")
        void shouldLogin() {
            CredentialsDto credentialsDto = new CredentialsDto("email@email.com", "password");

            when(loginJpaDao.login(credentialsDto)).thenReturn("token");

            String result = loginRepository.login(credentialsDto);

            assertEquals("token", result);
        }
    }
}
