package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.service.LoginService;
import es.artyhub.tienda_back.domain.dto.CredentialsDto;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.repository.LoginRepository;

public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;
    
    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
    
    @Override
    public String login(CredentialsDto credentialsDto) {
        if ( credentialsDto == null ) {
            throw new ValidationException("CredentialsDto cannot be null");
        }
        
        return loginRepository.login(credentialsDto);
    }
}
