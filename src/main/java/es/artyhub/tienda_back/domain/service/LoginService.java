package es.artyhub.tienda_back.domain.service;

import es.artyhub.tienda_back.domain.dto.CredentialsDto;

public interface LoginService {
    String login(CredentialsDto credentialsDto);
}
