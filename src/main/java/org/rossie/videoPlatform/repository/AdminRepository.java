package org.rossie.videoPlatform.repository;

import org.rossie.videoPlatform.model.Admin;
import org.rossie.videoPlatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findUserByEmail(String email);

    Optional<Admin> findByAuthToken(UUID authToken);

    Optional<Admin> findByResetToken(UUID resetToken);
}
