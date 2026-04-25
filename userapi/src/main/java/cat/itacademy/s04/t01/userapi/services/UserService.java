package cat.itacademy.s04.t01.userapi.services;

import cat.itacademy.s04.t01.userapi.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers(String name);
    Optional<User> getUserById(UUID id);
    User createUser(User user);
}