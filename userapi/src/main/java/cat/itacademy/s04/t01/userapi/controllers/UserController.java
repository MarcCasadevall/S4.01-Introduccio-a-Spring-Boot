package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.exceptions.UserNotFoundException;
import cat.itacademy.s04.t01.userapi.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/users")
public class UserController {
    private static List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getAllUsers(@RequestParam(required = false) String name) {

        if (name == null || name.isEmpty()) {
            return users;
        }
        return users.stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {

        user.setId(UUID.randomUUID());

        users.add(user);

        return user;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }
}
