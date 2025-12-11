package es.artyhub.tienda_back.persistence.dao.jpa;

import es.artyhub.tienda_back.persistence.dao.jpa.entity.ArtworkJpaEntity;

import java.util.List;

public interface ArtworkJpaDao extends GenericJpaDao<ArtworkJpaEntity>{
    List<ArtworkJpaEntity> findAllArtworksOfUser(Long id, int page, int size);
}
