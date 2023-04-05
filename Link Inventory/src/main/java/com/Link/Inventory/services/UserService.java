package com.Link.Inventory.services;

import com.Link.Inventory.controllers.models.User;
import com.Link.Inventory.controllers.models.enums.Role;
import com.Link.Inventory.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null){
            return false;
        }
        else{
            user.getRoles().add(Role.ROLE_INSTALLER);
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving new User: {}", user);
        userRepository.save(user);
        return true;
    }


    public List<User> list() {
        var listUser = userRepository.findAll();
        if (listUser.size() == 1){
            return null;
        }
        listUser.remove(0);
        return listUser;
    }

    public void banUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user.isActive()) {
            user.setActive(false);
            log.info("Ban user with email: {}", email);
        } else {
            user.setActive(true);
            log.info("Unban user with email: {}", email);
        }
        userRepository.save(user);
    }

    public void editUser(String email) {
        User user = userRepository.findByEmail(email);
        var role = user.getRoles().iterator().next();
        user.getRoles().clear();
        if (role == Role.ROLE_FOREMAN)
            user.getRoles().add(Role.ROLE_INSTALLER);
        else
            user.getRoles().add(Role.ROLE_FOREMAN);
        userRepository.save(user);
    }


    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
        log.info("Delete user with email: {}", email);
    }

    @Override
    public void run(String... args) throws Exception
    {
        if (userRepository.findByEmail("admin@yandex.ru") == null){
            User admin = new User();
            admin.setEmail("admin@yandex.ru");
            admin.setActive(true);
            admin.setName("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.getRoles().add(Role.ROLE_ADMIN);
            userRepository.save(admin);
        }
    }
}