package com.sokolowska.travelplannerapi.repository;

import com.sokolowska.travelplannerapi.model.CustomUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomUserRepository extends CrudRepository<CustomUser, Long> {

    Optional<CustomUser> findByEmail(String email);
}
