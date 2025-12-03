package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.exception.ResourceNotFoundException;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.ArtworkRepository;
import es.artyhub.tienda_back.domain.service.ArtworkService;
import java.util.Optional;

public class ArtworkServiceImpl implements ArtworkService {
    
    private final ArtworkRepository artworkRepository;

    public ArtworkServiceImpl(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    @Override
    public Page<ArtworkDto> findAll(int pageNumber, int pageSize) {
        return artworkRepository.findAll(pageNumber, pageSize);
    }

    @Override
    public Optional<ArtworkDto> findById(Long id) {
        return artworkRepository.findById(id);
    }

    @Override
    public ArtworkDto insert(ArtworkDto artworkDto) {
        if (findById(artworkDto.id()).isPresent()) {
            throw new BusinessException("Artwork with id " + artworkDto.id() + " already exists");
        }
        return artworkRepository.save(artworkDto);
    }

    @Override
    public ArtworkDto update(ArtworkDto artworkDto) {
        if (artworkRepository.findById(artworkDto.id()).isEmpty()) {
            throw new ResourceNotFoundException("Artwork with id " + artworkDto.id() + " not found");
        }
        return artworkRepository.save(artworkDto);
    }

    @Override
    public void delete(Long id) {
        artworkRepository.delete(id);
    }
}
