package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import es.artyhub.tienda_back.domain.dto.CredentialsDto;
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

    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public LoginJpaDaoImpl(UserJpaDao userJpaDao, SesionJpaDao sesionJpaDao) {
        this.userJpaDao = userJpaDao;
        this.sesionJpaDao = sesionJpaDao;
    }

    @Override
    public String login(CredentialsDto credentialsDto) {

        String email = credentialsDto.getEmail();

        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("EMAIL_CANNOT_BE_EMPTY");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("INVALID_EMAIL");
        }

        UserJpaEntity userJpaEntity = userJpaDao.findByEmail(email);

        if (userJpaEntity == null) {
            throw new ValidationException("USER_NOT_FOUND");
        }

        String password = credentialsDto.getPassword();

        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException("PASSWORD_CANNOT_BE_EMPTY");
        }

        if (!userJpaEntity.getPassword().equals(password)) {
            throw new ValidationException("INVALID_PASSWORD");
        }

        String token = UUID.randomUUID().toString();

        sesionJpaDao.insertSesion(new SesionJpaEntity(token, userJpaEntity.getId(), new Date()));
        return token;
    }
}
