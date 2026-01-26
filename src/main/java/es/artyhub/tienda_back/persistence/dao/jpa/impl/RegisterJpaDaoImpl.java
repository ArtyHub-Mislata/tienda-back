package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import es.artyhub.tienda_back.domain.dto.RegisterDto;
import es.artyhub.tienda_back.persistence.dao.jpa.RegisterJpaDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class RegisterJpaDaoImpl implements RegisterJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    public RegisterJpaDaoImpl() {
    }
    
    @Override
    public void register(RegisterDto registerDto) {
        entityManager.persist(registerDto);
    }
}
