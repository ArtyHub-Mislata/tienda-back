package es.artyhub.tienda_back.controller;

import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.service.ArtworkService;
import es.artyhub.tienda_back.domain.service.CategoryService;
import es.artyhub.tienda_back.domain.validation.DtoValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryAdminController {
    private final CategoryService categoryService;
    private final ArtworkService artworkService;
    public CategoryAdminController(CategoryService categoryService, ArtworkService artworkService) {
        this.categoryService = categoryService;
        this.artworkService = artworkService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        try {
            DtoValidator.validate(categoryDto);
            CategoryDto createCategoryDto = categoryService.insert(categoryDto);
            return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {
        try {
            if (!id.equals(categoryDto.getId())) {
                throw new ValidationException("ID in path and request body must match");
            }
            DtoValidator.validate(categoryDto);
            CategoryDto updateCategoryDto = categoryService.update(categoryDto);
            return new ResponseEntity<>(updateCategoryDto, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
