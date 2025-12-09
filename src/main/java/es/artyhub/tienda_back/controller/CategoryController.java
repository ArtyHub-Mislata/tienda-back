package es.artyhub.tienda_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.service.CategoryService;
import es.artyhub.tienda_back.domain.validation.DtoValidator;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    private final CategoryService categoryService;
    
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping 
    public ResponseEntity<Page<CategoryDto>> getAllCategories(@RequestParam(required = false, defaultValue = "1") int pageNumber,
                                                                          @RequestParam(required = false, defaultValue = "20") int pageSize) {
        
        Page<CategoryDto> categoryDtoPage = categoryService.findAll(pageNumber, pageSize);

        List<CategoryDto> categoryDetails = categoryDtoPage.data().stream()
            .toList();

        Page<CategoryDto> categoryDetailPage = new Page<>(
            categoryDetails,
            categoryDtoPage.pageNumber(),
            categoryDtoPage.pageSize(),
            categoryDtoPage.totalElements()
        );    
        return new ResponseEntity<>(categoryDetailPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        CategoryDto categoryDto = categoryService.findById(id);
        if (categoryDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
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
