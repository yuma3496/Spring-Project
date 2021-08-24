package net.myfarm.repository;

import net.myfarm.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    // User Registration
    public int insertOne(MUser user);

    // Get Users
    public List<MUser> findMany(MUser user);

    // User list
    public List<MUser> findMany();

    // Get a User
    public MUser findOne(String userId);

    // User Update (One user only)
    public void updateOne(@Param("userId") String userId,
    @Param(("password")) String password,
    @Param(("userName")) String userName);

    // User Delete (One user only)
    public int deleteOne(@Param(("userId")) String userId);

    // Get login user
    public MUser findLoginUser(String userId);
}
