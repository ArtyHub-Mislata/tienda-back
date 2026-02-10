package es.artyhub.tienda_back.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.tienda_back.domain.dto.CredentialsDto;
import es.artyhub.tienda_back.domain.repository.LoginRepository;
import es.artyhub.tienda_back.domain.service.impl.LoginServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LoginServiceImplTest {
    
    @Mock
    private LoginRepository loginRepository;
    
    @InjectMocks
    private LoginServiceImpl loginService;

    @Test
    @DisplayName("should return token when login is called with valid arguments")
    public void login_WhenLoginIsCalledWithValidArguments() {
        CredentialsDto credentialsDto = new CredentialsDto("user", "pass");
        
        when(loginRepository.login(credentialsDto)).thenReturn("token");

        String token = loginService.login(credentialsDto);

        assertEquals("token", token);
    }
}
