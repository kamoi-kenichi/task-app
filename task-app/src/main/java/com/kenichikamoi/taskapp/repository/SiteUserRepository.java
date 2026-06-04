package com.kenichikamoi.taskapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kenichikamoi.taskapp.entity.SiteUser;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {

    Optional<SiteUser> findByUsername(String username);
}