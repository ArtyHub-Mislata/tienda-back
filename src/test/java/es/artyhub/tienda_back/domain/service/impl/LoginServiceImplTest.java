package es.artyhub.tienda_back.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.tienda_back.domain.dto.CredentialsDto;
import es.artyhub.tienda_back.domain.repository.LoginRepository;

@ExtendWith(MockitoExtension.class)
public class LoginServiceImplTest {
    
    @Mock
    private LoginRepository loginRepository;

    @InjectMocks
    private LoginServiceImpl loginService;

    @Nested
    @DisplayName("Test login")
    class TestLogin {
        @Test
        @DisplayName("While credentials is valid should return token")
        void whileCredentialsIsValid_ShouldReturnToken() {
            CredentialsDto credentialsDto = new CredentialsDto("email", "password");

            when(loginRepository.login(credentialsDto)).thenReturn("token");

            assertDoesNotThrow(() -> loginService.login(credentialsDto));

            Mockito.verify(loginRepository).login(credentialsDto);
        }
    }
}
