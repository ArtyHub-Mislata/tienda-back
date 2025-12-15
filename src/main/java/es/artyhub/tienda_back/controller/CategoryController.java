package es.artyhub.tienda_back.controller;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.service.ArtworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.service.CategoryService;
import es.artyhub.tienda_back.domain.validation.DtoValidator;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
public class CategoryController {
    
    private final CategoryService categoryService;
    private final ArtworkService artworkService;
    public CategoryController(CategoryService categoryService, ArtworkService artworkService) {
        this.categoryService = categoryService;
        this.artworkService = artworkService;
    }

    @GetMapping("/categories")
    public ResponseEntity<Page<CategoryDto>> getAllCategories(@RequestParam(required = false, defaultValue = "1") int pageNumber,
                                                                          @RequestParam(required = false, defaultValue = "20") int pageSize) {
        Page<CategoryDto> categoryDtoPage = categoryService.findAll(pageNumber, pageSize);

        return new ResponseEntity<>(categoryDtoPage, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        CategoryDto categoryDto = categoryService.findById(id);
        if (categoryDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }
    @GetMapping("/categories/{id}/artworks")
    public ResponseEntity<Page<ArtworkDto>> getArtworksByCategory(@RequestParam(required = false, defaultValue = "1") int pageNumber,
                                                                  @RequestParam(required = false, defaultValue = "20") int pageSize,
                                                                  @PathVariable Long id){
        Page<ArtworkDto> artworkDtoPage = artworkService.findAllArtworksByCategoryId(pageNumber, pageSize,id);

        return new ResponseEntity<>(artworkDtoPage, HttpStatus.OK);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        try {
            DtoValidator.validate(categoryDto);
            CategoryDto createCategoryDto = categoryService.insert(categoryDto);
            return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/admin/categories/{id}")
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

    @DeleteMapping("/admin/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
