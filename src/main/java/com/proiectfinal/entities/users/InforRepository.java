package com.proiectfinal.entities.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InforRepository extends JpaRepository<Info,Long> {
}
