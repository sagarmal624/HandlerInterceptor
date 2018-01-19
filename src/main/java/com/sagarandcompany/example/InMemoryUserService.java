package com.sagarandcompany.example;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserService implements UserService {
    private List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        User user = new User();
        user.setId(1L);
        user.setName("admin");
        user.setEmailAddress("admin@example.com");
        user.setPassword("password");
        users.add(user);
    }

    @Override
    public User loginUser(User user) {
        Optional<User> loggedInUser = users.stream()
                .filter(u -> validateUser(u, user))
                .findAny();
        return loggedInUser.isPresent() ? loggedInUser.get() : null;
    }

    private static boolean validateUser(User existingUser, User user) {
        return existingUser.getEmailAddress()
                .equals(user.getEmailAddress())
                && existingUser.getPassword()
                .equals(user.getPassword());
    }
}