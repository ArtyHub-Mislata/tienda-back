package es.artyhub.tienda_back.persistence.repository.impl;

import es.artyhub.tienda_back.domain.repository.LoginRepository;
import es.artyhub.tienda_back.persistence.dao.jpa.LoginJpaDao;
import es.artyhub.tienda_back.domain.dto.CredentialsDto;

public class LoginRepositoryImpl implements LoginRepository {
    
    private final LoginJpaDao loginJpaDao;

    public LoginRepositoryImpl(LoginJpaDao loginJpaDao) {
        this.loginJpaDao = loginJpaDao;
    }

    @Override
    public String login(CredentialsDto credentialsDto) {
        return loginJpaDao.login(credentialsDto);
    }
}
