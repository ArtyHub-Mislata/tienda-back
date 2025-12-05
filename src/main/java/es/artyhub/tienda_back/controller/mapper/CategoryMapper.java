package es.artyhub.tienda_back.controller.mapper;

import es.artyhub.tienda_back.controller.webmodel.response.CategoryDetailResponse;
import es.artyhub.tienda_back.controller.webmodel.response.CategorySummaryResponse;
import es.artyhub.tienda_back.domain.dto.CategoryDto;

public class CategoryMapper {
    public static CategorySummaryResponse fromCategoryDtoToCategorySummaryResponse(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        return new CategorySummaryResponse(
            categoryDto.getId(),
            categoryDto.getName()
        );
    }

    public static CategoryDetailResponse fromCategoryDtoToCategoryDetailResponse(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        return new CategoryDetailResponse(
            categoryDto.getId(),
            categoryDto.getName()
        );
    }
}
