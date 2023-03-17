package com.example.calculatorspring.Repository;

import com.example.calculatorspring.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Override
    Optional<User> findById(String s);

    User findByUsernameAndPassword(String username, String password);
    @Override
    <S extends User> S save(S user);
}