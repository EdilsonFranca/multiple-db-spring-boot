package com.javatodev.api.repository.db2;

import com.javatodev.api.model.db1.UserEntity1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User2Repository extends JpaRepository<UserEntity1, Long> {}
