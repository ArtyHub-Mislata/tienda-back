package es.artyhub.tienda_back.persistence.repository.impl;

import es.artyhub.tienda_back.domain.repository.SesionRepository;
import es.artyhub.tienda_back.domain.dto.SesionDto;
import es.artyhub.tienda_back.persistence.dao.jpa.SesionJpaDao;
import es.artyhub.tienda_back.persistence.repository.mapper.SesionMapper;
import java.util.Optional;

public class SesionRepositoryImpl implements SesionRepository {

    private final SesionJpaDao sesionJpaDao;

    public SesionRepositoryImpl(SesionJpaDao sesionJpaDao) {
        this.sesionJpaDao = sesionJpaDao;
    }
    
    @Override
    public Optional<SesionDto> findByToken(String token) {
        return sesionJpaDao.findByToken(token).map(SesionMapper.getInstance()::fromSesionJpaEntityToSesionDto);
    }

    @Override
    public void deleteSesion(String token) {
        sesionJpaDao.deleteSesion(token);
    }

    @Override
    public void insertSesion(SesionDto sesion) {
        sesionJpaDao.insertSesion(SesionMapper.getInstance().fromSesionDtoToSesionJpaEntity(sesion));
    }
}
