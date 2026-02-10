package es.artyhub.tienda_back.domain.service;

import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.CategoryRepository;
import es.artyhub.tienda_back.domain.service.impl.CategoryServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {
    
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Nested
    @DisplayName("findAll")
    public class FindAll {
        @Test
        @DisplayName("should return page of categories when getAll is called with valid arguments")
        public void findAll() {
            int pageNumber = 1;
            int pageSize = 10;

            CategoryDto categoryDto = new CategoryDto();
            List<CategoryDto> categories = List.of(categoryDto);
            Page<CategoryDto> page = new Page<>(categories, pageNumber, pageSize, categories.size());

            when(categoryRepository.findAll(pageNumber, pageSize)).thenReturn(page);

            Page<CategoryDto> result = categoryService.findAll(pageNumber, pageSize);

            assertNotNull(result);
            assertEquals(page, result);
        }
    }

    @Nested
    @DisplayName("findById")
    public class FindById {
        @Test
        @DisplayName("should return category when category exists")
        public void findById_WhenCategoryExists() {
            Long categoryId = 1L;

            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(categoryId);

            when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(categoryDto));

            CategoryDto result = categoryService.findById(categoryId);

            assertNotNull(result);
            assertEquals(categoryDto, result);
        }

        @Test
        @DisplayName("should throw BusinessException when category does not exist")
        public void findById_WhenCategoryDoesNotExist() {
            Long categoryId = 1L;

            when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> categoryService.findById(categoryId));
        }
    }

    @Nested
    @DisplayName("insert")
    public class Insert {
        @Test
        @DisplayName("should return created category when insert is called with valid arguments")
        public void insert() {
            CategoryDto categoryDto = new CategoryDto(1L, "Name");

            when(categoryRepository.save(categoryDto)).thenReturn(categoryDto);

            CategoryDto result = categoryService.insert(categoryDto);

            assertNotNull(result);
            assertEquals(categoryDto, result);
        }
    }

    @Nested
    @DisplayName("update")
    public class Update {
        @Test
        @DisplayName("should return updated category when update is called with valid arguments")
        public void update_WhenCategoryExists() {
            CategoryDto categoryDto = new CategoryDto(1L, "Name");

            when(categoryRepository.findById(categoryDto.getId())).thenReturn(Optional.of(categoryDto));
            when(categoryRepository.save(categoryDto)).thenReturn(categoryDto);

            CategoryDto result = categoryService.update(categoryDto);

            assertNotNull(result);
            assertEquals(categoryDto, result);
        }

        @Test
        @DisplayName("should throw BusinessException when category does not exist")
        public void update_WhenCategoryDoesNotExist() {
            CategoryDto categoryDto = new CategoryDto(1L, "Name");

            when(categoryRepository.findById(categoryDto.getId())).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> categoryService.update(categoryDto));
        }
    }

    @Nested
    @DisplayName("delete")
    public class Delete {
        @Test
        @DisplayName("should delete category when delete is called with valid arguments")
        public void delete_WhenCategoryExists() {
            Long categoryId = 1L;
            CategoryDto categoryDto = new CategoryDto(categoryId, "Name");

            when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(categoryDto));

            categoryService.delete(categoryId);

            verify(categoryRepository).delete(categoryId);
        }

        @Test
        @DisplayName("should throw BusinessException when category does not exist")
        public void delete_WhenCategoryDoesNotExist() {
            Long categoryId = 1L;

            when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> categoryService.delete(categoryId));
        }
    }
}
