package es.artyhub.tienda_back.domain.service.impl;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.tienda_back.domain.repository.UserRepository;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.exception.ValidationException;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    
    @Nested
    @DisplayName("Find all users")
    class FindAllUsers {
        
        @Test
        @DisplayName("While users exist should return list of users")
        void whileUsersExist_ShouldReturnListOfUsers() {

            int page = 0;
            int size = 10;
            
            List<UserDto> users = List.of(
                new UserDto(1L, "name1", "email1", "password1", "1111111111111111", "description1", "address1", "imageProfileUrl1", List.of()),
                new UserDto(2L, "name2", "email2", "password2", "2222222222222222", "description2", "address2", "imageProfileUrl2", List.of()),
                new UserDto(3L, "name3", "email3", "password3", "3333333333333333", "description3", "address3", "imageProfileUrl3", List.of())
            );
            
            when(userRepository.findAll(page, size)).thenReturn(new Page<>(users, page, size, users.size()));
            
            Page<UserDto> result = userServiceImpl.findAll(page, size);
            
            assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(users.size(), result.data().size(), "Result size should be equal to users size"),
                () -> assertEquals(users.getFirst().id(), result.data().getFirst().id(), "Result first id should be equal to users first id"),
                () -> assertEquals(users.get(2).id(), result.data().get(2).id(), "Result second id should be equal to users second id"),
                () -> assertEquals(users.get(3).id(), result.data().get(3).id(), "Result third id should be equal to users third id"),
                () -> assertEquals(users.getLast().id(), result.data().getLast().id(), "Result last id should be equal to users last id")
            );
            
            Mockito.verify(userRepository).findAll(page, size);
        }

        @Test
        @DisplayName("While no users exist should return empty list")
        void whileNoUsersExist_ShouldReturnEmptyList() {
            int page = 0;
            int size = 10;
            
            when(userRepository.findAll(page, size)).thenReturn(new Page<>(List.of(), page, size, 0));
            
            Page<UserDto> result = userServiceImpl.findAll(page, size);
            
            assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(result.data().size(), 0, "Result size should be equal to 0")
            );
            
            Mockito.verify(userRepository).findAll(page, size);
        }
    }

    @Nested
    @DisplayName("Find user by id")
    class FindUserById {
        
        @Test
        @DisplayName("While user exists should return user")
        void whileUserExists_ShouldReturnUser() {
            Long id = 1L;

            when(userRepository.findById(id)).thenReturn(Optional.of(new UserDto(id, "name", "email", "password", "1111111111111111", "description", "address", "imageProfileUrl", List.of())));
        
            Optional<UserDto> result = userServiceImpl.findById(id);

            assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(result.get().id(), id, "Result id should be equal to id")
            );

            Mockito.verify(userRepository).findById(id);
        }

        @Test
        @DisplayName("While no user exists should throw BusinessException")
        void whileNoUserExists_ShouldThrowBusinessException() {
            Long id = 1L;

            when(userRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> userServiceImpl.findById(id));

            Mockito.verify(userRepository).findById(id);
        }
    }

    @Nested
    @DisplayName("Create user")
    class CreateUser {
        
        @Test
        @DisplayName("While user is valid should create user")
        void whileUserIsValid_ShouldCreateUser() {
            UserDto userDto = new UserDto(1L, "name", "email", "password", "1111111111111111", "description", "address", "imageProfileUrl", List.of());

            when(userRepository.findById(userDto.id())).thenReturn(Optional.empty());
            when(userRepository.save(userDto)).thenReturn(userDto);

            UserDto result = userServiceImpl.insert(userDto);

            assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(result.id(), userDto.id(), "Result id should be equal to userDto id"),
                () -> assertEquals(result.name(), userDto.name(), "Result name should be equal to userDto name"),
                () -> assertEquals(result.email(), userDto.email(), "Result email should be equal to userDto email"),
                () -> assertEquals(result.password(), userDto.password(), "Result password should be equal to userDto password"),
                () -> assertEquals(result.nAccount(), userDto.nAccount(), "Result nAccount should be equal to userDto nAccount"),
                () -> assertEquals(result.description(), userDto.description(), "Result description should be equal to userDto description"),
                () -> assertEquals(result.address(), userDto.address(), "Result address should be equal to userDto address"),
                () -> assertEquals(result.imageProfileUrl(), userDto.imageProfileUrl(), "Result imageProfileUrl should be equal to userDto imageProfileUrl")
            );

            Mockito.verify(userRepository).save(userDto);
        }

        @Test
        @DisplayName("While user exists should throw BusinessException")
        void whileUserExists_ShouldThrowBusinessException() {
            UserDto userDto = new UserDto(1L, "name", "email", "password", "1111111111111111", "description", "address", "imageProfileUrl", List.of());

            when(userRepository.findById(userDto.id())).thenReturn(Optional.of(userDto));

            assertThrows(BusinessException.class, () -> userServiceImpl.insert(userDto));

            Mockito.verify(userRepository).findById(userDto.id());
            Mockito.verify(userRepository, never()).save(userDto);
        }

        @Test
        @DisplayName("While user have no valid name should throw ValidationException")
        void whileUserHaveNoValidName_ShouldThrowValidationException() {
            UserDto userDto = new UserDto(1L, "", "email", "password", "1111111111111111", "description", "address", "imageProfileUrl", List.of());

            assertThrows(ValidationException.class, () -> userServiceImpl.insert(userDto));

            Mockito.verify(userRepository, never()).save(userDto);
        }

        @Test
        @DisplayName("While user have no valid email should throw ValidationException")
        void whileUserHaveNoValidEmail_ShouldThrowValidationException() {
            UserDto userDto = new UserDto(1L, "name", "", "password", "1111111111111111", "description", "address", "imageProfileUrl", List.of());

            assertThrows(ValidationException.class, () -> userServiceImpl.insert(userDto));

            Mockito.verify(userRepository, never()).save(userDto);
        }

        @Test
        @DisplayName("While user have no valid password should throw ValidationException")
        void whileUserHaveNoValidPassword_ShouldThrowValidationException() {
            UserDto userDto = new UserDto(1L, "name", "email", "", "1111111111111111", "description", "address", "imageProfileUrl", List.of());

            assertThrows(ValidationException.class, () -> userServiceImpl.insert(userDto));

            Mockito.verify(userRepository, never()).save(userDto);
        }

        @Test
        @DisplayName("While user have no valid nAccount should throw ValidationException")
        void whileUserHaveNoValidNAccount_ShouldThrowValidationException() {
            UserDto userDto = new UserDto(1L, "name", "email", "password", "", "description", "address", "imageProfileUrl", List.of());

            assertThrows(ValidationException.class, () -> userServiceImpl.insert(userDto));

            Mockito.verify(userRepository, never()).save(userDto);
        }

        @Test
        @DisplayName("While user have a nAccount with length less than 16 should throw ValidationException")
        void whileUserHaveNAccountWithLengthLessThan16_ShouldThrowValidationException() {
            UserDto userDto = new UserDto(1L, "name", "email", "password", "11111111111111", "description", "address", "imageProfileUrl", List.of());

            assertThrows(ValidationException.class, () -> userServiceImpl.insert(userDto));

            Mockito.verify(userRepository, never()).save(userDto);
        }

        @Test
        @DisplayName("While user have a nAccount with length more than 16 should throw ValidationException")
        void whileUserHaveNAccountWithLengthMoreThan16_ShouldThrowValidationException() {
            UserDto userDto = new UserDto(1L, "name", "email", "password", "111111111111111111", "description", "address", "imageProfileUrl", List.of());

            assertThrows(ValidationException.class, () -> userServiceImpl.insert(userDto));

            Mockito.verify(userRepository, never()).save(userDto);
        }

        @Test
        @DisplayName("While user have null description should throw ValidationException")
        void whileUserHaveNullDescription_ShouldThrowValidationException() {
            UserDto userDto = new UserDto(1L, "name", "email", "password", "1111111111111111", null, "address", "imageProfileUrl", List.of());

            assertThrows(ValidationException.class, () -> userServiceImpl.insert(userDto));

            Mockito.verify(userRepository, never()).save(userDto);
        }

        @Test
        @DisplayName("While user have no valid address should throw ValidationException")
        void whileUserHaveNoValidAddress_ShouldThrowValidationException() {
            UserDto userDto = new UserDto(1L, "name", "email", "password", "1111111111111111", "description", "", "imageProfileUrl", List.of());

            assertThrows(ValidationException.class, () -> userServiceImpl.insert(userDto));

            Mockito.verify(userRepository, never()).save(userDto);
        }

        @Test
        @DisplayName("While user have null imageProfileUrl should throw ValidationException")
        void whileUserHaveNullImageProfileUrl_ShouldThrowValidationException() {
            UserDto userDto = new UserDto(1L, "name", "email", "password", "1111111111111111", "description", "address", null, List.of());

            assertThrows(ValidationException.class, () -> userServiceImpl.insert(userDto));

            Mockito.verify(userRepository, never()).save(userDto);
        }
    }

    @Nested
    @DisplayName("Update user")
    class UpdateUser {
        
        @Test
        @DisplayName("While user exists should update user")
        void whileUserExists_ShouldUpdateUser() {
            UserDto userDto = new UserDto(1L, "name", "email", "password", "1111111111111111", "description", "address", "imageProfileUrl", List.of());
            UserDto userDtoUpdated = new UserDto(1L, "updatedName", "updatedEmail", "password", "1111111111111111", "description", "address", "imageProfileUrl", List.of());

            when(userRepository.findById(userDto.id())).thenReturn(Optional.of(userDto));
            when(userRepository.save(userDtoUpdated)).thenReturn(userDtoUpdated);

            UserDto result = userServiceImpl.update(userDtoUpdated);

            assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(result.id(), userDtoUpdated.id(), "Result id should be equal to userDtoUpdated id"),
                () -> assertEquals(result.name(), "updatedName", "Result name should be equal to userDtoUpdated name"),
                () -> assertEquals(result.email(), "updatedEmail", "Result email should be equal to userDtoUpdated email")
            );

            Mockito.verify(userRepository).findById(userDto.id());
            Mockito.verify(userRepository).save(userDtoUpdated);
        }

        @Test
        @DisplayName("While user not exists should throw BusinessException")
        void whileUserNotExists_ShouldThrowBusinessException() {
            UserDto userDto = new UserDto(1L, "name", "email", "password", "1111111111111111", "description", "address", "imageProfileUrl", List.of());

            when(userRepository.findById(userDto.id())).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> userServiceImpl.update(userDto));

            Mockito.verify(userRepository).findById(userDto.id());
            Mockito.verify(userRepository, never()).save(userDto);
        }
    }

    @Nested
    @DisplayName("Delete user")
    class DeleteUser {
        
        @Test
        @DisplayName("While user exists should delete user")
        void whileUserExists_ShouldDeleteUser() {
            UserDto userDto = new UserDto(1L, "name", "email", "password", "1111111111111111", "description", "address", "imageProfileUrl", List.of());

            when(userRepository.findById(userDto.id())).thenReturn(Optional.of(userDto));

            userServiceImpl.delete(userDto.id());

            Mockito.verify(userRepository).findById(userDto.id());
            Mockito.verify(userRepository).delete(userDto.id());
        }

        @Test
        @DisplayName("While user not exists should throw BusinessException")
        void whileUserNotExists_ShouldThrowBusinessException() {
            Long id = 1L;

            when(userRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> userServiceImpl.delete(id));

            Mockito.verify(userRepository).findById(id);
            Mockito.verify(userRepository, never()).delete(id);
        }
    }
}
