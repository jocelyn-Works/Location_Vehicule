package com.example.user.dao;

import com.example.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //List<User> findAll();
    //User findById(int id);
    //List<User> save(User u);
    //List<User> update(User u, int id);
    //List<User> delete(int id);

    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

}
