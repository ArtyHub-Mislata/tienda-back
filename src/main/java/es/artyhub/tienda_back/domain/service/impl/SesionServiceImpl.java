package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.service.SesionService;
import es.artyhub.tienda_back.domain.repository.SesionRepository;
import es.artyhub.tienda_back.domain.dto.SesionDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;

import java.util.Optional;

public class SesionServiceImpl implements SesionService {
    
    private final SesionRepository sesionRepository;

    public SesionServiceImpl(SesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    @Override
    public SesionDto insertSesion(SesionDto sesionDto) {
        if (findSesionByToken(sesionDto.getToken()) != null) {
            throw new BusinessException("Sesion already exists");
        }
        return sesionRepository.insertSesion(sesionDto);
    }

    @Override
    public void deleteSesion(String token) {
        if (findSesionByToken(token) == null) {
            throw new BusinessException("Sesion not found");
        }
        sesionRepository.deleteSesion(token);
    }

    @Override
    public SesionDto findSesionByToken(String token) {
        Optional<SesionDto> sesionDto = sesionRepository.findByToken(token);
        return sesionDto.orElse(null);
    }
}
