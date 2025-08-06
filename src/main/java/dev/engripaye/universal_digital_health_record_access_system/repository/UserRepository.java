package dev.engripaye.universal_digital_health_record_access_system.repository;

import dev.engripaye.universal_digital_health_record_access_system.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByOauthId(String oauthId);
    Optional<User> findByEmail(String email);
}
