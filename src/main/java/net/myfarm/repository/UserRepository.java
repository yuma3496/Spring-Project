package net.myfarm.repository;

import net.myfarm.domain.user.model.MUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MUser, String> {
}
