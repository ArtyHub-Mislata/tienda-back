package es.artyhub.tienda_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.service.CategoryService;
import es.artyhub.tienda_back.domain.validation.DtoValidator;
import es.artyhub.tienda_back.controller.webmodel.response.CategoryDetailResponse;
import es.artyhub.tienda_back.controller.mapper.CategoryMapper;
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
    public ResponseEntity<Page<CategoryDetailResponse>> getAllCategories(@RequestParam(required = false, defaultValue = "1") int pageNumber,
                                                                          @RequestParam(required = false, defaultValue = "20") int pageSize) {
        
        Page<CategoryDto> categoryDtoPage = categoryService.findAll(pageNumber, pageSize);

        List<CategoryDetailResponse> categoryDetails = categoryDtoPage.data().stream()
            .map(CategoryMapper::fromCategoryDtoToCategoryDetailResponse)
            .toList();

        Page<CategoryDetailResponse> categoryDetailPage = new Page<>(
            categoryDetails,
            categoryDtoPage.pageNumber(),
            categoryDtoPage.pageSize(),
            categoryDtoPage.totalElements()
        );    
        return new ResponseEntity<>(categoryDetailPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetailResponse> getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id)
            .map(CategoryMapper::fromCategoryDtoToCategoryDetailResponse)
            .map(categoryDetailResponse -> new ResponseEntity<>(categoryDetailResponse,HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CategoryDetailResponse> createCategory(@RequestBody CategoryDto categoryDto) {
        try {
            DtoValidator.validate(categoryDto);
            CategoryDto createCategoryDto = categoryService.insert(categoryDto);
            CategoryDetailResponse categoryDetailResponse = CategoryMapper.fromCategoryDtoToCategoryDetailResponse(createCategoryDto);
            return new ResponseEntity<>(categoryDetailResponse, HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDetailResponse> updateCategory(@RequestBody CategoryDto categoryDto) {
        try {
            DtoValidator.validate(categoryDto);
            CategoryDto updateCategoryDto = categoryService.update(categoryDto);
            CategoryDetailResponse categoryDetailResponse = CategoryMapper.fromCategoryDtoToCategoryDetailResponse(updateCategoryDto);
            return new ResponseEntity<>(categoryDetailResponse, HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
