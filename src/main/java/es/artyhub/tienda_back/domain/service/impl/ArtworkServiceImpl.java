package es.artyhub.tienda_back.domain.service.impl;

import java.math.BigDecimal;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.ArtworkRepository;
import es.artyhub.tienda_back.domain.service.ArtworkService;

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
    public ArtworkDto findById(Long id) {
        return artworkRepository.findById(id).orElseThrow(() -> new BusinessException("Artwork with id " + id + " not found"));
    }

    @Override
    public ArtworkDto insert(ArtworkDto artworkDto) {
        if (artworkRepository.findById(artworkDto.id()).isPresent()) {
            throw new BusinessException("Artwork with id " + artworkDto.id() + " already exists");
        } else if (artworkDto.name() == null || artworkDto.name().trim().isEmpty()) {
            throw new ValidationException("Artwork name is required");
        } else if (artworkDto.description() == null) {
            throw new ValidationException("Artwork description cannot be null");
        } else if (artworkDto.image() == null || artworkDto.image().trim().isEmpty()) {
            throw new ValidationException("Artwork image is required");
        } else if (artworkDto.price() == null) {
            throw new ValidationException("Artwork price cannot be null");
        } else if (artworkDto.price().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Artwork price cannot be negative");
        } else if (artworkDto.stock() == null) {
            throw new ValidationException("Artwork stock cannot be null");
        } else if (artworkDto.stock() < 0) {
            throw new ValidationException("Artwork stock cannot be negative");
        } else if (artworkDto.categoryDto() == null) {
            throw new ValidationException("Artwork category cannot be null");
        } else {
            return artworkRepository.save(artworkDto);
        }
    }

    @Override
    public ArtworkDto update(ArtworkDto artworkDto) {
        if (artworkRepository.findById(artworkDto.id()).isEmpty()) {
            throw new BusinessException("Artwork with id " + artworkDto.id() + " not found");
        }
        return artworkRepository.save(artworkDto);
    }

    @Override
    public void delete(Long id) {
        if (artworkRepository.findById(id).isEmpty()) {
            throw new BusinessException("Artwork with id " + id + " not found");
        }
        artworkRepository.delete(id);
    }
}
