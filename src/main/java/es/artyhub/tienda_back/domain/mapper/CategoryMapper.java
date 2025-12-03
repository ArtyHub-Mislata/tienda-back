package es.artyhub.tienda_back.domain.mapper;

import es.artyhub.tienda_back.domain.model.Category;
import es.artyhub.tienda_back.domain.dto.CategoryDto;

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
            return null;
        }
        return new Category(
            categoryDto.id(), 
            categoryDto.name()
        );
    }

    public CategoryDto fromCategoryToCategoryDto(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDto(
            category.getId(),
            category.getName()
        );
    }
}
