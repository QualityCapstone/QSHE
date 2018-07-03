package com.codeup.qshe.repositories;

import com.codeup.qshe.models.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfilesRepository extends JpaRepository <UserProfile, Long> {
}
