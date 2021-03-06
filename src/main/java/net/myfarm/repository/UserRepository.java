package net.myfarm.repository;

import net.myfarm.domain.user.model.MUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<MUser, String> {

    // Search Login User
    @Query("select user"
            + "from MUser user"
            + "where userId = :userId")
    public MUser findLoginUser(@Param("userId") String userId);

    // Update user
    @Modifying
    @Query("update MUser" +
            + " set"
            + " password = :password"
            + " , userName = :userName"
            + " where"
            + " userId = :userId")
    public Integer updateUser(@Param("userId") String userId,
                              @Param("password") String password,
                              @Param("userName") String userName);
}
