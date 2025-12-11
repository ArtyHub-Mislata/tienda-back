package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import java.util.Date;
import java.util.UUID;

import es.artyhub.tienda_back.domain.dto.CredentialsDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.persistence.dao.jpa.LoginJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.SesionJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.UserJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.SesionJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class LoginJpaDaoImpl implements LoginJpaDao {
    
    @PersistenceContext
    private EntityManager entityManager;

    private final UserJpaDao userJpaDao;
    private final SesionJpaDao sesionJpaDao;

    public LoginJpaDaoImpl(UserJpaDao userJpaDao, SesionJpaDao sesionJpaDao) {
        this.userJpaDao = userJpaDao;
        this.sesionJpaDao = sesionJpaDao;
    }

    @Override
    public String login(CredentialsDto credentialsDto) {
        UserJpaEntity userJpaEntity = userJpaDao.findByEmail(credentialsDto.getEmail());

        if (userJpaEntity == null) {
            throw new ValidationException("User not found");
        }

        if (!userJpaEntity.getPassword().equals(credentialsDto.getPassword())) {
            throw new ValidationException("Invalid password");
        }

        if (userJpaEntity.getRole() == UserRole.USER) {
            throw new ValidationException("Invalid role");
        }

        String token = UUID.randomUUID().toString();

        sesionJpaDao.insertSesion(new SesionJpaEntity(token, userJpaEntity.getId(), new Date()));
        return token;
    }
}
