package cat.itacademy.s04.t01.userapi.services;

import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(String name) {
        List<User> allUsers = userRepository.findAll();
        if (name != null && !name.isEmpty()) {
            return allUsers.stream()
                    .filter(u -> u.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return allUsers;
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        user.setId(UUID.randomUUID()); // La lógica de negocio vive aquí
        return userRepository.save(user);
    }
}
