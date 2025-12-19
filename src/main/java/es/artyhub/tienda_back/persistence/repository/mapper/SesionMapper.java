package es.artyhub.tienda_back.persistence.repository.mapper;

import es.artyhub.tienda_back.domain.dto.SesionDto;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.SesionJpaEntity;

public class SesionMapper {
    private static SesionMapper instance;

    private SesionMapper() {
    }
    
    public static SesionMapper getInstance() {
        if (instance == null) {
            instance = new SesionMapper();
        }
        return instance;
    }
    
    public SesionDto fromSesionJpaEntityToSesionDto(SesionJpaEntity sesionJpaEntity) {
        if (sesionJpaEntity == null) {
            return null;
        }
        return new SesionDto(
                sesionJpaEntity.getToken(),
                sesionJpaEntity.getUserId(),
                sesionJpaEntity.getDateCreate()
        );
    }
    
    public SesionJpaEntity fromSesionDtoToSesionJpaEntity(SesionDto sesionDto) {
        if (sesionDto == null) {
            return null;
        }
        return new SesionJpaEntity(
                sesionDto.getToken(),
                sesionDto.getUserId(),
                sesionDto.getCreatedAt()
        );
    }
}
