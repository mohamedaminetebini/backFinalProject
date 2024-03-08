package com.example.finalprojetback.Reposetories;

import com.example.finalprojetback.Models.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRpo extends JpaRepository<UserModel, String> {
    Optional<UserModel> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE UserModel SET profileStatus = ?2 WHERE id = ?1")
    Optional<UserModel>  updateStatus(String id, String status);

}
