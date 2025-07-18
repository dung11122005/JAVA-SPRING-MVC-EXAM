package com.example.exam_portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.exam_portal.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User save(User hoidanit);

    void deleteById(long id);

    List<User> findByRole_Name(String roleName);

    List<User> findOneByEmail(String email);

    List<User> findAll();

    User findById(long id);

    boolean existsByEmail(String email);

    User findByEmail(String email);
}
