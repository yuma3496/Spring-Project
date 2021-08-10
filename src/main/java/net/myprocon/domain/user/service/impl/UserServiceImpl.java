package net.myprocon.domain.user.service.impl;

import net.myprocon.domain.user.model.MUser;
import net.myprocon.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import net.myprocon.repository.UserMapper;

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
}
