package net.myprocon.myprocon.service.impl;

import net.myprocon.myprocon.domain.user.model.MUser;
import net.myprocon.myprocon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import repository.UserMapper;

public class UserServiceImpl extends UserService {

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
