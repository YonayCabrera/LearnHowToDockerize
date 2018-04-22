package com.backend.repository;

import com.backend.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BackEndRepository {
    List<User> getAll();
}
