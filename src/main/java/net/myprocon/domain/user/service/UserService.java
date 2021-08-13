package net.myprocon.domain.user.service;

import net.myprocon.domain.user.model.MUser;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    // User Registration
    public void signup(MUser user);

    // Get User List
    public List<MUser> getUsers();
}
