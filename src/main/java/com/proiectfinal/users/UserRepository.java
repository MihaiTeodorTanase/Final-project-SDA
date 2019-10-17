package com.proiectfinal.users;

import com.proiectfinal.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
 interface UserRepository extends JpaRepository<UserModel, Long>{
}
