package net.myfarm.domain.user.service.impl;

import net.myfarm.domain.user.model.MUser;
import net.myfarm.domain.user.service.UserService;
import net.myfarm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceImpl2 implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    // User Registration
    @Transactional
    @Override
    public void signup(MUser user) {
        // Check existence
        boolean exists = repository.existsById(user.getUserId());
        if (exists) {
            throw new DataAccessException("User already exits") {
            };
        }

        user.setDepartmentId(1);
        user.setRole("ROLE_GENERAL");

        // Password Encryption
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));

        // Insert
        repository.save(user);

        // Get user list
        @Override
        public List<MUser> getUsers(MUser user) {

            // Search Condition
            ExampleMatcher matcher = ExampleMatcher
                    .matching() // and Condition
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // Like
                    .withIgnoreCase(); // Uppercase & Lowercase

            return repository.findAll(Example.of(user, matcher));
    }

        // Get one user
        @Override
        public MUser getUserOne(String userId) {
            Optional<MUser> option = repository.findById(userId);
            MUser user = option.orElse(null);
            return user;
        }

        // User update (one user)
        @Transactional
        @Override
        public void updateUserOne(String userId, String password, String userName) {

            // Password Encryption
            String encryptPassword = encoder.encode(password);

            // Update user
            repository.updateUser(userId, encryptPassword, userName);
        }

        // Delete user (one user)
        @Override
        public MUser getLoginUser(String userId) {
            Optional<MUser> option = repository.findById(userId);
            MUser user = option.orElse(null);
            return user;
        }

        // Get login user
        @Override
        public MUser getLoginUser(String userId) {
            return repository.findLoginUser(userId);
        }
    }
}