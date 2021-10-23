package com.SamuelFumeroHdez.backend.repository;

import com.SamuelFumeroHdez.backend.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserName(String username);
}
