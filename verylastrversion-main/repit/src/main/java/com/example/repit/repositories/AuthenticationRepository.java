package com.example.repit.repositories;

import com.example.repit.entities.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Integer> {
    Optional<Authentication> findBylogin(String string);

}
