package es.artyhub.tienda_back.persistence.dao.jpa;

import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;

public interface UserJpaDao extends GenericJpaDao<UserJpaEntity> {
    UserJpaEntity findByEmail(String email);
}
