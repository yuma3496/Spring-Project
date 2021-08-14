package net.myfarm.domain.user.service;

import net.myfarm.domain.user.model.MUser;

import java.util.List;

public interface UserService {

    // User Registration
    public void signup(MUser user);

    // Get User List
    public List<MUser> getUsers();
}
