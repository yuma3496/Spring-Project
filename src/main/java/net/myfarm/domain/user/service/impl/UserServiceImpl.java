package net.myfarm.domain.user.service.impl;

import net.myfarm.domain.user.model.MUser;
import net.myfarm.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import net.myfarm.repository.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    // User Registration
    @Override
    public void signup(MUser user) {
        user.setDepartmentId(1); //Department
        user.setRole("ROLE_GENERAL"); //Role
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
    @Override
    public void updateUserOne(String userId,
                              String password,
                              String userName) {
        mapper.updateOne(userId, password, userName);
    }

    // Delete One User
    @Override
    public void deleteUserOne(String userId) {
        int count = mapper.deleteOne(userId);
    }
}
