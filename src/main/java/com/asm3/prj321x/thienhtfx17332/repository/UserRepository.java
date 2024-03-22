package com.asm3.prj321x.thienhtfx17332.repository;

import com.asm3.prj321x.thienhtfx17332.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);
    User findUserById(int userId);





}
