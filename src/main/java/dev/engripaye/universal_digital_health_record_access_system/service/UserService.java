package dev.engripaye.universal_digital_health_record_access_system.service;

import dev.engripaye.universal_digital_health_record_access_system.model.Role;
import dev.engripaye.universal_digital_health_record_access_system.model.User;
import dev.engripaye.universal_digital_health_record_access_system.repository.UserRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User upsertFromOidc(OidcUser oidcUser){
        String sub = oidcUser.getSubject(); // unique id from provider
        Optional<User> maybe = userRepository.findByOauthId(sub);
        User user = maybe.orElseGet(User::new);

        user.setOauthId(sub);
        user.setEmail(oidcUser.getEmail());
        user.setName(oidcUser.getFullName() != null ? oidcUser.getFullName() : oidcUser.getEmail());

        if (user.getRoles() == null){

            Set<Role> roles = new HashSet<>();
            roles.add(Role.ROLE_PATIENT);
            user.setRoles(roles);
        }
        return userRepository.save(user);
    }
}
