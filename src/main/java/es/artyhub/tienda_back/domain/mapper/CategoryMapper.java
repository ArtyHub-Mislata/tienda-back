package es.artyhub.tienda_back.domain.mapper;

import es.artyhub.tienda_back.domain.model.Category;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;

public class CategoryMapper {
    private static CategoryMapper instance;

    public CategoryMapper() {
    }

    public static CategoryMapper getInstance() {
        if (instance == null) {
            instance = new CategoryMapper();
        }
        return instance;
    }

    public Category fromCategoryDtoToCategory(CategoryDto categoryDto) {
        if (categoryDto == null) {
            throw new BusinessException("CategoryDto cannot be null");
        }
        return new Category(
            categoryDto.getId(), 
            categoryDto.getName()
        );
    }

    public CategoryDto fromCategoryToCategoryDto(Category category) {
        if (category == null) {
            throw new BusinessException("Category cannot be null");
        }
        return new CategoryDto(
            category.getId(),
            category.getName()
        );
    }
}
