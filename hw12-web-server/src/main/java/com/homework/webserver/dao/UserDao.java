package com.homework.webserver.dao;

import java.util.Optional;
import com.homework.webserver.model.User;

public interface UserDao {

    Optional<User> findById(long id);

    Optional<User> findRandomUser();

    Optional<User> findByLogin(String login);
}
