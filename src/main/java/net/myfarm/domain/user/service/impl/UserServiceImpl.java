package net.myfarm.domain.user.service.impl;

import net.myfarm.domain.user.model.MUser;
import net.myfarm.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import net.myfarm.repository.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    // User Registration
    @Override
    public void signup(MUser user) {
        user.setDepartmentId(1); //Department
        user.setRole("ROLE_GENERAL"); //Role

        // Password Encryption
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));

        mapper.insertOne(user);
    }

    // Get User List
    @Override
    public List<MUser> getUsers(MUser user) {
        return mapper.findMany(user);
    }

    // Get One User
    @Override
    public MUser getUserOne(String userId) {
        return mapper.findOne(userId);
    }

    // Update One User
    @Transactional
    @Override
    public void updateUserOne(String userId,
                              String password,
                              String userName) {

        // Password Encryption
        String encryptPassword = encoder.encode(password);

        mapper.updateOne(userId, encryptPassword, userName);

        // Exception
        int i = 1 / 0;
    }

    // Delete One User
    @Override
    public void deleteUserOne(String userId) {
        int count = mapper.deleteOne(userId);
    }
}
