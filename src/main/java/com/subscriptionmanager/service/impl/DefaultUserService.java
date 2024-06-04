package com.subscriptionmanager.service.impl;

import com.subscriptionmanager.dto.UserDTO;
import com.subscriptionmanager.entity.User;
import com.subscriptionmanager.repository.UserRepository;
import com.subscriptionmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<User> create(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public ResponseEntity<User> getById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent() && optionalUser.get().isActive()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<User> update(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent() && optionalUser.get().isActive()) {
            User user = optionalUser.get();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());

            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<User> delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(false);
            userRepository.save(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll().stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
    }
}