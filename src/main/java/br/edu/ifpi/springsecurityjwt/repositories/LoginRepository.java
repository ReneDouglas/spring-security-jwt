package br.edu.ifpi.springsecurityjwt.repositories;

import br.edu.ifpi.springsecurityjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
