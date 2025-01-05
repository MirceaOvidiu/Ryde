package com.example.ryde.service;

import com.example.ryde.model.Manager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManagerService {
    void saveManager(Manager manager);
    List<Manager> getAllManagers();
    void deleteManager(Long id);
}