package com.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chat.model.User;

/**
 * 
 * @author manish
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByUsername(String username);

	User findByUsername(String username);

	List<User> findByUsernameNot(String username);

}
