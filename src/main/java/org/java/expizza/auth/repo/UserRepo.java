package org.java.expizza.auth.repo;

import java.util.Optional;

import org.java.expizza.auth.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String username);
}