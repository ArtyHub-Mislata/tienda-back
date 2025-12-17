package es.artyhub.tienda_back.controller;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.service.ArtworkService;
import es.artyhub.tienda_back.domain.validation.DtoValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/artworks")
public class ArtworkAdminController {
    private final ArtworkService artworkService;

    public ArtworkAdminController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }
    @PostMapping
    public ResponseEntity<ArtworkDto> createArtwork(@RequestBody ArtworkDto artworkDto) {
        try {
            DtoValidator.validate(artworkDto);
            ArtworkDto createArtworkDto = artworkService.insert(artworkDto);
            return new ResponseEntity<>(createArtworkDto, HttpStatus.CREATED);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtworkDto> updateArtwork(@PathVariable("id") Long id, @RequestBody ArtworkDto artworkDto) {
        try {
            if (!id.equals(artworkDto.getId())) {
                throw new ValidationException("ID in path and request body must match");
            }
            DtoValidator.validate(artworkDto);
            ArtworkDto updateArtworkDto = artworkService.update(artworkDto);
            return new ResponseEntity<>(updateArtworkDto, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtwork(@PathVariable Long id) {
        artworkService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
