package es.artyhub.tienda_back.persistence.repository.impl;

import es.artyhub.tienda_back.domain.repository.SesionRepository;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.SesionJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.SesionJpaDao;

public class SesionRepositoryImpl implements SesionRepository {

    private final SesionJpaDao sesionJpaDao;

    public SesionRepositoryImpl(SesionJpaDao sesionJpaDao) {
        this.sesionJpaDao = sesionJpaDao;
    }
    
    @Override
    public SesionJpaEntity findByToken(String token) {
        return sesionJpaDao.findByToken(token);
    }

    @Override
    public void deleteSesion(String token) {
        sesionJpaDao.deleteSesion(token);
    }

    @Override
    public void insertSesion(SesionJpaEntity sesion) {
        sesionJpaDao.insertSesion(sesion);
        
    }
}
