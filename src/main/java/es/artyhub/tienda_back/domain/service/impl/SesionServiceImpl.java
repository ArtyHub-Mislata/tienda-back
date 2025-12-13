package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.service.SesionService;
import es.artyhub.tienda_back.domain.repository.SesionRepository;
import es.artyhub.tienda_back.domain.dto.SesionDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;

public class SesionServiceImpl implements SesionService {
    
    private final SesionRepository sesionRepository;

    public SesionServiceImpl(SesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    @Override
    public void insertSesion(SesionDto sesionDto) {
        if (isValidToken(sesionDto.getToken())) {
            throw new BusinessException("Sesion already exists");
        }
        sesionRepository.insertSesion(sesionDto);
    }

    @Override
    public void deleteSesion(String token) {
        if (!isValidToken(token)) {
            throw new BusinessException("Sesion not found");
        }
        sesionRepository.deleteSesion(token);
    }

    @Override
    public boolean isValidToken(String token) {
        return sesionRepository.findByToken(token).isPresent();
    }
}
