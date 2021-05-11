package com.example.demo.repository;

import com.example.demo.entities.User;
import com.example.demo.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByPassportNumber(String passportNumber);
    UserProjection findByPassportNumberIs(String passportNumber);

}
