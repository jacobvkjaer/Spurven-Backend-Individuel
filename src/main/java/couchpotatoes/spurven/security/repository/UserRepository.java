package couchpotatoes.spurven.security.repository;

import couchpotatoes.spurven.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
UserRepository extends JpaRepository<User,String> {
}
