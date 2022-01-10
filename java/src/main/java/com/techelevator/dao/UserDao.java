package com.techelevator.dao;

import com.techelevator.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    Long findIdByUsername(String username);

    boolean create(String username, String password, String role);
}
