package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u from User u")
    List<User> getAllUsers();

    boolean existsByUsername(String username);
}
