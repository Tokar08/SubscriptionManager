package com.subscriptionmanager.service;

import com.subscriptionmanager.dto.UserDTO;
import com.subscriptionmanager.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<User> create(UserDTO userDTO);

    ResponseEntity<User> getById(Long id);

    ResponseEntity<User> update(Long id, UserDTO userDTO);

    ResponseEntity<User> delete(Long id);

    List<User> getAll();

}
