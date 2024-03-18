package com.kober.encryptingapi.entity.repository;

import com.kober.encryptingapi.entity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}
