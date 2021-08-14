package net.myfarm.repository;

import net.myfarm.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // User Registration
    public int insertOne(MUser user);

    // User list
    public List<MUser> findMany();

    // Get a User
    public MUser findOne(String userId);
}
