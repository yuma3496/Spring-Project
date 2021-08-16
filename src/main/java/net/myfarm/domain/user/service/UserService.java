package net.myfarm.domain.user.service;

import net.myfarm.domain.user.model.MUser;

import java.util.List;

public interface UserService {

    // User Registration
    public void signup(MUser user);

    // Get User List
    public List<MUser> getUsers(MUser user);

    // Get One User ID
    public MUser getUserOne(String userId);

    // Update User (one user only)
    public void updateUserOne(String userId,
                              String password,
                              String userName);

    // Delete User (one user only)
    public void deleteUserOne(String userId);
}
