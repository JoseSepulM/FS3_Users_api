package com.example.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.users.model.Users;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long>{

    Optional<Users> findByUsername(String username);
    
}
