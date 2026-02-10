package es.artyhub.tienda_back.domain.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.ArtworkRepository;
import es.artyhub.tienda_back.domain.repository.UserRepository;
import es.artyhub.tienda_back.domain.service.impl.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private ArtworkRepository artworkRepository;

    @InjectMocks
    private UserServiceImpl userService;
    
    @Nested
    @DisplayName("findAll")
    public class FindAll {
        @Test
        @DisplayName("should return page of users when getAll is called with valid arguments")
        public void findAll() {
            int pageNumber = 1;
            int pageSize = 10;

            UserDto userDto = new UserDto();
            List<UserDto> users = List.of(userDto);
            Page<UserDto> page = new Page<>(users, pageNumber, pageSize, users.size());

            when(userRepository.findAll(pageNumber, pageSize)).thenReturn(page);

            Page<UserDto> result = userService.findAll(pageNumber, pageSize);

            assertNotNull(result);
            assertEquals(page, result);
        }
    }

    @Nested
    @DisplayName("findAllArtworksByUserId")
    public class FindAllArtworksByUserId {
        @Test
        @DisplayName("should return list of artworks when user exists")
        public void findAllArtworksByUserId_WhenUserExists() {
            int pageNumber = 1;
            int pageSize = 10;
            Long userId = 1L;

            UserDto userDto = new UserDto(userId, "Name", "Email", "Password", "Description", "Address", "Image", UserRole.USER);

            CategoryDto categoryDto = new CategoryDto(1L, "Name");

            ArtworkDto artworkDto = new ArtworkDto(1L, "Name", "Description", "Email", new BigDecimal(10), categoryDto, userDto, 10L);
            List<ArtworkDto> artworks = List.of(artworkDto);
            Page<ArtworkDto> artworkPage = new Page<>(artworks, pageNumber, pageSize, artworks.size());

            when(artworkRepository.findAllArtworksOfUser(userId, pageNumber, pageSize)).thenReturn(artworkPage);

            Page<ArtworkDto> result = userService.findAllArtworks(userId, pageNumber, pageSize);

            assertNotNull(result);
            assertEquals(artworkPage, result);
        }
    }

    @Nested
    @DisplayName("findById")
    public class FindById {
        @Test
        @DisplayName("should return user when user exists")
        public void findById_WhenUserExists() {
            Long userId = 1L;

            UserDto userDto = new UserDto(userId, "Name", "Email", "Password", "Description", "Address", "Image", UserRole.USER);

            when(userRepository.findById(userId)).thenReturn(Optional.of(userDto));

            UserDto result = userService.findById(userId);

            assertNotNull(result);
            assertEquals(userDto, result);
        }

        @Test
        @DisplayName("should throw BusinessException when user does not exist")
        public void findById_WhenUserDoesNotExist() {
            Long userId = 1L;

            when(userRepository.findById(userId)).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> userService.findById(userId));
        }
    }

    @Nested
    @DisplayName("insert")
    public class Insert {
        @Test
        @DisplayName("should return created user when insert is called with valid arguments")
        public void insert() {
            UserDto userDto = new UserDto(1L, "Name", "Email", "Password", "Description", "Address", "Image", UserRole.USER);

            when(userRepository.save(userDto)).thenReturn(userDto);

            UserDto result = userService.insert(userDto);

            assertNotNull(result);
            assertEquals(userDto, result);
        }
    }

    @Nested
    @DisplayName("update")
    public class Update {
        @Test
        @DisplayName("should return updated user when update is called with valid arguments")
        public void update_WhenUserExists() {
            UserDto userDto = new UserDto(1L, "Name", "Email", "Password", "Description", "Address", "Image", UserRole.USER);

            when(userRepository.findById(userDto.getId())).thenReturn(Optional.of(userDto));
            when(userRepository.save(userDto)).thenReturn(userDto);

            UserDto result = userService.update(userDto);

            assertNotNull(result);
            assertEquals(userDto, result);
        }

        @Test
        @DisplayName("should throw BusinessException when user does not exist")
        public void update_WhenUserDoesNotExist() {
            UserDto userDto = new UserDto(1L, "Name", "Email", "Password", "Description", "Address", "Image", UserRole.USER);

            when(userRepository.findById(userDto.getId())).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> userService.update(userDto));
        }
    }

    @Nested
    @DisplayName("delete")
    public class Delete {
        @Test
        @DisplayName("should delete user when delete is called with valid arguments")
        public void delete_WhenUserExists() {
            Long userId = 1L;
            UserDto userDto = new UserDto(userId, "Name", "Email", "Password", "Description", "Address", "Image", UserRole.USER);

            when(userRepository.findById(userId)).thenReturn(Optional.of(userDto));

            userService.delete(userId);

            verify(userRepository).delete(userId);
        }

        @Test
        @DisplayName("should throw BusinessException when user does not exist")
        public void delete_WhenUserDoesNotExist() {
            Long userId = 1L;

            when(userRepository.findById(userId)).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> userService.delete(userId));
        }
    }
}
