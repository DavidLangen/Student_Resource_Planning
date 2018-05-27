package com.david.Repository;

import com.david.Entity.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<LoginUser, Long> {

    LoginUser findByUsername(String Username);

}
