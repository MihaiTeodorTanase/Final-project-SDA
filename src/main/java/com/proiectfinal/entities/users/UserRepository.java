package com.proiectfinal.entities.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
 interface UserRepository extends JpaRepository<UserModel, Long>{

Optional<UserModel> findByUsername(@Param("username") String username);
Optional<UserModel> findByEmail(@Param("email") String email);

}
