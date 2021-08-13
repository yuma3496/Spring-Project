package net.myprocon.repository;

import net.myprocon.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // User Registration
    public int insertOne(MUser user);

    // User list
    public List<MUser> findMany();
}
