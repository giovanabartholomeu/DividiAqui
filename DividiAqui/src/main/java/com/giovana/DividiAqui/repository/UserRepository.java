package com.giovana.DividiAqui.repository;

import com.giovana.DividiAqui.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query ("SELECT u FROM User u WHERE u.name = :name")
    Optional<User> findByName(@Param ("name") String name);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}
