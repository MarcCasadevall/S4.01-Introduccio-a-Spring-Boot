package cat.itacademy.s04.t01.userapi.services;

import cat.itacademy.s04.t01.userapi.exceptions.EmailAlreadyExistsException;
import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers(String name) {
        if (name != null && !name.isEmpty()) {
            return userRepository.searchByName(name);
        }
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + user.getEmail());
        }
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }
}