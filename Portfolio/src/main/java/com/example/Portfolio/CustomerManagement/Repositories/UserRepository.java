package com.example.Portfolio.CustomerManagement.Repositories;

import com.example.Portfolio.CustomerManagement.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
