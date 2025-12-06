package es.artyhub.tienda_back.persistence.repository.impl;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.ArtworkRepository;
import es.artyhub.tienda_back.persistence.dao.jpa.ArtworkJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.ArtworkJpaEntity;
import es.artyhub.tienda_back.persistence.repository.mapper.ArtworkMapper;

import java.util.List;
import java.util.Optional;

public class ArtworkRepositoryImpl implements ArtworkRepository {

    private final ArtworkJpaDao artworkJpaDao;

    public ArtworkRepositoryImpl(ArtworkJpaDao artworkJpaDao) {
        this.artworkJpaDao = artworkJpaDao;
    }

    @Override
    public Page<ArtworkDto> findAll(int pageNumber, int pageSize) {
        List<ArtworkDto> listaArtworkDto = artworkJpaDao
                .findAll(pageNumber, pageSize)
                .stream()
                .map(ArtworkMapper.getInstance()::fromArtworkJpaEntityToArtworkDto)
                .toList();
        long totalElements = artworkJpaDao.count();

        return new Page<>(
                listaArtworkDto,
                pageNumber,
                pageSize,
                totalElements
        );
    }

    @Override
    public Optional<ArtworkDto> findById(Long id) {
        return artworkJpaDao
                .findById(id)
                .map(ArtworkMapper.getInstance()::fromArtworkJpaEntityToArtworkDto);
    }

    @Override
    public ArtworkDto save(ArtworkDto artwork) {
        ArtworkJpaEntity artworkJpaEntity = ArtworkMapper.getInstance().fromArtworkDtoToArtworkJpaEntity(artwork);
        if (artwork.getId() == null) {
            return ArtworkMapper.getInstance().fromArtworkJpaEntityToArtworkDto(artworkJpaDao.insert(artworkJpaEntity));
        }
        return ArtworkMapper.getInstance().fromArtworkJpaEntityToArtworkDto(artworkJpaDao.update(artworkJpaEntity));

    }

    @Override
    public void delete(Long id) {
        artworkJpaDao.deleteById(id);
    }
}
