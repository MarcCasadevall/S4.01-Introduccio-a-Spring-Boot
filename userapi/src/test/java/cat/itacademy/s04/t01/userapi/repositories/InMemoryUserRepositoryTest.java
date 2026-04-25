package cat.itacademy.s04.t01.userapi.repositories;

import cat.itacademy.s04.t01.userapi.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserRepositoryTest {

    private InMemoryUserRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryUserRepository();
    }

    @Test
    void save_shouldStoreUser() {
        User user = new User(UUID.randomUUID(), "Marc", "marc@email.com");

        User savedUser = repository.save(user);

        assertEquals(user, savedUser);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void save_shouldUpdateUserIfSameIdExists() {
        UUID id = UUID.randomUUID();
        User original = new User(id, "Marc", "marc@email.com");
        User updated = new User(id, "Marc Updated", "marc@email.com");

        repository.save(original);
        repository.save(updated);

        assertEquals(1, repository.findAll().size());
        assertEquals("Marc Updated", repository.findById(id).get().getName());
    }

    @Test
    void findAll_shouldReturnAllUsers() {
        repository.save(new User(UUID.randomUUID(), "Marc", "marc@email.com"));
        repository.save(new User(UUID.randomUUID(), "Anna", "anna@email.com"));

        List<User> result = repository.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void findById_shouldReturnUser_whenExists() {
        UUID id = UUID.randomUUID();
        repository.save(new User(id, "Marc", "marc@email.com"));

        Optional<User> result = repository.findById(id);

        assertTrue(result.isPresent());
        assertEquals("Marc", result.get().getName());
    }

    @Test
    void findById_shouldReturnEmpty_whenNotExists() {
        Optional<User> result = repository.findById(UUID.randomUUID());

        assertTrue(result.isEmpty());
    }

    @Test
    void searchByName_shouldReturnMatchingUsers() {
        repository.save(new User(UUID.randomUUID(), "Marc Garcia", "marc@email.com"));
        repository.save(new User(UUID.randomUUID(), "Anna Marc", "anna@email.com"));
        repository.save(new User(UUID.randomUUID(), "Pere Puig", "pere@email.com"));

        List<User> result = repository.searchByName("marc");

        assertEquals(2, result.size());
    }

    @Test
    void existsByEmail_shouldReturnTrue_whenEmailExists() {
        repository.save(new User(UUID.randomUUID(), "Marc", "marc@email.com"));

        assertTrue(repository.existsByEmail("marc@email.com"));
    }

    @Test
    void existsByEmail_shouldReturnFalse_whenEmailNotExists() {
        assertFalse(repository.existsByEmail("noexiste@email.com"));
    }
}