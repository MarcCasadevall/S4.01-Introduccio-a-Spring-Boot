package cat.itacademy.s04.t01.userapi.services;

import cat.itacademy.s04.t01.userapi.exceptions.EmailAlreadyExistsException;
import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void createUser_shouldThrowException_whenEmailAlreadyExists() {

        User user = new User(null, "Marc", "marc@email.com");
        when(userRepository.existsByEmail("marc@email.com")).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(user));
        verify(userRepository, never()).save(any());
    }

    @Test
    void createUser_shouldGenerateUUID_andSaveUser_whenEmailIsNew() {

        User user = new User(null, "Marc", "marc@email.com");
        when(userRepository.existsByEmail("marc@email.com")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = userService.createUser(user);

        assertNotNull(result.getId());
        verify(userRepository, times(1)).save(user);
    }



    @Test
    void getAllUsers_shouldReturnAllUsers_whenNameIsNull() {

        when(userRepository.findAll()).thenReturn(List.of(
                new User(UUID.randomUUID(), "Marc", "marc@email.com"),
                new User(UUID.randomUUID(), "Anna", "anna@email.com")
        ));

        List<User> result = userService.getAllUsers(null);

        assertEquals(2, result.size());
        verify(userRepository).findAll();
        verify(userRepository, never()).searchByName(any());
    }

    @Test
    void getAllUsers_shouldSearchByName_whenNameIsProvided() {

        when(userRepository.searchByName("Marc")).thenReturn(List.of(
                new User(UUID.randomUUID(), "Marc", "marc@email.com")
        ));

        List<User> result = userService.getAllUsers("Marc");

        assertEquals(1, result.size());
        verify(userRepository).searchByName("Marc");
        verify(userRepository, never()).findAll();
    }


    @Test
    void getUserById_shouldReturnUser_whenExists() {
        // GIVEN
        UUID id = UUID.randomUUID();
        User user = new User(id, "Marc", "marc@email.com");
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // WHEN
        Optional<User> result = userService.getUserById(id);

        // THEN
        assertTrue(result.isPresent());
        assertEquals("Marc", result.get().getName());
    }

    @Test
    void getUserById_shouldReturnEmpty_whenNotExists() {

        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(id);

        assertTrue(result.isEmpty());
    }
}