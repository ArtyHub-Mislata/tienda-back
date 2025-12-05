package es.artyhub.tienda_back.domain.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Nested
    @DisplayName("Find all categories")
    class FindAllCategories {
        
        @Test
        @DisplayName("While categories exist should return list of categories")
        void whileCategoriesExist_ShouldReturnListOfCategories() {
            int page = 1;
            int size = 10;

            List<CategoryDto> categories = List.of(
                new CategoryDto(1L, "category1"),
                new CategoryDto(2L, "category2"),
                new CategoryDto(3L, "category3"),
                new CategoryDto(4L, "category4"),
                new CategoryDto(5L, "category5")
            );

            when(categoryRepository.findAll(page, size)).thenReturn(new Page<>(categories, page, size, categories.size()));

            Page<CategoryDto> result = categoryService.findAll(page, size);

            assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(categories.size(), result.data().size(), "Result size should be equal to categories size"),
                () -> assertEquals(categories.getFirst().getId(), result.data().getFirst().getId(), "Result first id should be equal to categories first id"),
                () -> assertEquals(categories.get(2).getId(), result.data().get(2).getId(), "Result second id should be equal to categories second id"),
                () -> assertEquals(categories.get(3).getId(), result.data().get(3).getId(), "Result third id should be equal to categories third id"),
                () -> assertEquals(categories.get(4).getId(), result.data().get(4).getId(), "Result fourth id should be equal to categories fourth id"),
                () -> assertEquals(categories.getLast().getId(), result.data().getLast().getId(), "Result last id should be equal to categories last id")
            );

            Mockito.verify(categoryRepository).findAll(page, size);
        }

        @Test
        @DisplayName("While no categories exist should return empty list")
        void whileNoCategoriesExist_ShouldReturnEmptyList() {
            int page = 1;
            int size = 10;

            Page<CategoryDto> categories = new Page<>(List.of(), page, size, 0);

            when(categoryRepository.findAll(page, size)).thenReturn(categories);

            Page<CategoryDto> result = categoryService.findAll(page, size);

            assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(categories.data().size(), result.data().size(), "Result size should be equal to categories size"),
                () -> assertEquals(categories.data().getFirst().getId(), result.data().getFirst().getId(), "Result first id should be equal to categories first id"),
                () -> assertEquals(categories.data().get(2).getId(), result.data().get(2).getId(), "Result second id should be equal to categories second id"),
                () -> assertEquals(categories.data().get(3).getId(), result.data().get(3).getId(), "Result third id should be equal to categories third id"),
                () -> assertEquals(categories.data().get(4).getId(), result.data().get(4).getId(), "Result fourth id should be equal to categories fourth id"),
                () -> assertEquals(categories.data().getLast().getId(), result.data().getLast().getId(), "Result last id should be equal to categories last id")
            );

            Mockito.verify(categoryRepository).findAll(page, size);
        }
    }

    @Nested
    @DisplayName("Find category by id")
    class FindCategoryById {

        @Test
        @DisplayName("While category exists should return category")
        void whileCategoryExists_ShouldReturnCategory() {
            Long id = 1L;
            Optional<CategoryDto> categoryDto = Optional.of(new CategoryDto(id, "category"));

            when(categoryRepository.findById(id)).thenReturn(categoryDto);

            CategoryDto result = categoryService.findById(id);

            assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(categoryDto.get().getId(), result.getId(), "Result id should be equal to categoryDto id"),
                () -> assertEquals(categoryDto.get().getName(), result.getName(), "Result name should be equal to categoryDto name")
            );

            Mockito.verify(categoryRepository).findById(id);
        }
        
        @Test
        @DisplayName("While no category exists should throw BusinessException")
        void whileNoCategoryExists_ShouldThrowBusinessException() {
            Long id = 1L;
            Optional<CategoryDto> categoryDto = Optional.empty();

            when(categoryRepository.findById(id)).thenReturn(categoryDto);

            assertThrows(BusinessException.class, () -> categoryService.findById(id));

            Mockito.verify(categoryRepository).findById(id);
        }
    }

    @Nested
    @DisplayName("Create category")
    class CreateCategory {
        
        @Test
        @DisplayName("While category is valid should create category")
        void whileCategoryIsValid_ShouldCreateCategory() {
            CategoryDto categoryDto = new CategoryDto(1L, "category");
            
            when(categoryRepository.findById(categoryDto.getId())).thenReturn(Optional.empty());
            when(categoryRepository.save(categoryDto)).thenReturn(categoryDto);

            CategoryDto result = categoryService.insert(categoryDto);

            assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(categoryDto.getId(), result.getId(), "Result id should be equal to categoryDto id"),
                () -> assertEquals(categoryDto.getName(), result.getName(), "Result name should be equal to categoryDto name")
            );

            Mockito.verify(categoryRepository).findById(categoryDto.getId());
            Mockito.verify(categoryRepository).save(categoryDto);
        }

        @Test
        @DisplayName("While category exists should throw BusinessException")
        void whileCategoryExists_ShouldThrowBusinessException() {
            CategoryDto categoryDto = new CategoryDto(1L, "category");
            
            when(categoryRepository.findById(categoryDto.getId())).thenReturn(Optional.of(categoryDto));

            assertThrows(BusinessException.class, () -> categoryService.insert(categoryDto));

            Mockito.verify(categoryRepository).findById(categoryDto.getId());
            Mockito.verify(categoryRepository, never()).save(categoryDto);
        }

        @Test
        @DisplayName("While category have no valid name should throw ValidationException")
        void whileCategoryHaveNoValidName_ShouldThrowValidationException() {
            CategoryDto categoryDto = new CategoryDto(1L, "");

            assertThrows(ValidationException.class, () -> categoryService.insert(categoryDto));

            Mockito.verify(categoryRepository, never()).save(categoryDto);
        }
    }

    @Nested
    @DisplayName("Update category")
    class UpdateCategory {
        
        @Test
        @DisplayName("While category is valid should update category")
        void whileCategoryIsValid_ShouldUpdateCategory() {
            CategoryDto categoryDto = new CategoryDto(1L, "category");
            CategoryDto updatedCategoryDto = new CategoryDto(1L, "updated category");
            
            when(categoryRepository.findById(categoryDto.getId())).thenReturn(Optional.of(categoryDto));
            when(categoryRepository.save(updatedCategoryDto)).thenReturn(updatedCategoryDto);

            CategoryDto result = categoryService.update(updatedCategoryDto);

            assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(updatedCategoryDto.getId(), result.getId(), "Result id should be equal to categoryDto id"),
                () -> assertEquals("updated category", result.getName(), "Result name should be equal to categoryDto name")
            );

            Mockito.verify(categoryRepository).save(updatedCategoryDto);
        }

        @Test
        @DisplayName("While category does not exist should throw BusinessException")
        void whileCategoryDoesNotExist_ShouldThrowBusinessException() {
            CategoryDto categoryDto = new CategoryDto(1L, "category");
            
            when(categoryRepository.findById(categoryDto.getId())).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> categoryService.update(categoryDto));

            Mockito.verify(categoryRepository).findById(categoryDto.getId());
            Mockito.verify(categoryRepository, never()).save(categoryDto);
        }
    }

    @Nested
    @DisplayName("Delete category")
    class DeleteCategory {
        
        @Test
        @DisplayName("While category exists should delete category")
        void whileCategoryExists_ShouldDeleteCategory() {
            CategoryDto categoryDto = new CategoryDto(1L, "category");
            
            when(categoryRepository.findById(categoryDto.getId())).thenReturn(Optional.of(categoryDto));

            categoryService.delete(categoryDto.getId());

            Mockito.verify(categoryRepository).delete(categoryDto.getId());
        }

        @Test
        @DisplayName("While no category exists should throw BusinessException")
        void whileNoCategoryExists_ShouldThrowBusinessException() {
            Long id = 1L;
            
            when(categoryRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> categoryService.delete(id));

            Mockito.verify(categoryRepository).findById(id);
            Mockito.verify(categoryRepository, never()).delete(id);
        }
    }
}
