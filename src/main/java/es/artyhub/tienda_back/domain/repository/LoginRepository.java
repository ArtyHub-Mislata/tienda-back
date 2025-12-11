package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.domain.dto.CredentialsDto;

public interface LoginRepository {
    String login(CredentialsDto credentialsDto);
}
