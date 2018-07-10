package com.codeup.qshe.repositories;

import com.codeup.qshe.models.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfiles extends JpaRepository <UserProfile, Long> {


}
