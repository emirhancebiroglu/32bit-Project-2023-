package com.bit.shared.repository;

import com.bit.shared.entity.Role;
import com.bit.shared.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
   Optional<User> findByEmail(String username);

   @Query("SELECT u FROM User u JOIN u.roles r WHERE r IN :roles")
   Optional<User> findByRoles(Collection<Role> roles);
}