package com.backend.actions;

import com.backend.domain.User;
import com.backend.repository.BackEndRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsers {
    private BackEndRepository backEndRepository;

    public GetAllUsers(BackEndRepository backEndRepository) {

        this.backEndRepository = backEndRepository;
    }

    public List<User> execute() {
        return backEndRepository.getAll();
    }
}
