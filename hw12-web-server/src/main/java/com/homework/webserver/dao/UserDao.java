package com.homework.webserver.dao;

import com.homework.webserver.model.User;
import java.util.Optional;

public interface UserDao {

    Optional<User> findById(long id);

    Optional<User> findRandomUser();

    Optional<User> findByLogin(String login);
}
