package es.artyhub.tienda_back.persistence.dao.jpa;

import es.artyhub.tienda_back.domain.dto.CredentialsDto;

public interface LoginJpaDao {
    String login(CredentialsDto credentialsDto);
}
