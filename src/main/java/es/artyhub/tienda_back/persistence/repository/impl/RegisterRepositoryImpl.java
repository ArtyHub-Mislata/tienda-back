package es.artyhub.tienda_back.persistence.repository.impl;

import es.artyhub.tienda_back.domain.dto.RegisterDto;
import es.artyhub.tienda_back.domain.repository.RegisterRepository;
import es.artyhub.tienda_back.persistence.dao.jpa.RegisterJpaDao;

public class RegisterRepositoryImpl implements RegisterRepository {

    private final RegisterJpaDao registerJpaDao;

    public RegisterRepositoryImpl(RegisterJpaDao registerJpaDao) {
        this.registerJpaDao = registerJpaDao;
    }
    
    @Override
    public void register(RegisterDto registerDto) {
        registerJpaDao.register(registerDto);
    }
}
