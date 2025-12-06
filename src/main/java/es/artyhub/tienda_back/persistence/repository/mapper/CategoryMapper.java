package es.artyhub.tienda_back.persistence.repository.mapper;

import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;

public class CategoryMapper {
    private static CategoryMapper instance;

    private CategoryMapper() {
    }

    public static CategoryMapper getInstance() {
        if (instance == null) {
            instance = new CategoryMapper();
        }
        return instance;
    }

    public CategoryDto fromCategoryJpaEntityToCategoryDto(CategoryJpaEntity categoryJpaEntity) {
        if (categoryJpaEntity == null) {
            return null;
        }
        return new CategoryDto(
                categoryJpaEntity.getId(),
                categoryJpaEntity.getNombre()
        );
    }

    public CategoryJpaEntity fromCategoryDtoToCategoryJpaEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }

        return new CategoryJpaEntity(
                categoryDto.getId(),
                categoryDto.getName()
        );
    }
}
