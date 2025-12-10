package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.service.SesionService;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.SesionJpaEntity;
import es.artyhub.tienda_back.domain.repository.SesionRepository;

public class SesionServiceImpl implements SesionService {
    
    private final SesionRepository sesionRepository;

    public SesionServiceImpl(SesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    @Override
    public void insertSesion(SesionJpaEntity sesionJpaEntity) {
        sesionRepository.insertSesion(sesionJpaEntity);
    }

    @Override
    public void deleteSesion(String token) {
        if (isValidToken(token)) {
            sesionRepository.deleteSesion(token);
        }
    }

    @Override
    public boolean isValidToken(String token) {
        return sesionRepository.findByToken(token) != null;
    }
}
