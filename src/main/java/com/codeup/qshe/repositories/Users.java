package com.codeup.qshe.repositories;



import com.codeup.qshe.models.user.User;
import com.codeup.qshe.models.user.UserConnection;
import com.codeup.qshe.models.user.UserProfile;
import com.codeup.qshe.models.user.UserWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface Users extends JpaRepository<User, Long> {

    User findByUsername(String username);


    void deleteByUsername(String username);


    @Query("select u.profile from User u where u.id = ?1")
    UserProfile getUserProfile(Long userId);

    @Query("select up from UserProfile up where up.username = ?1")
    UserProfile getUserProfileByUsername(String username);

    @Query("select uc from UserConnection uc where userId = ?1")
    UserConnection getUserConnection(String userId);

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query(value = "INSERT into users(username,password) values(?1,?2)", nativeQuery = true)
    void addUser(String username, String password);

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query(value = "INSERT into user_roles(user_id,role) values(?1,?2)", nativeQuery = true)
    void addRole(Long userId, String role);

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query(value = "INSERT into user_roles(user_id,role) values(?1,'USER')", nativeQuery = true)
    void addDefaultRole(Long userId);

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query(value = "INSERT into user_profile(email, first_name, last_name, name, username)" +
            "VALUES(?1,?2,?3,?4,?5)", nativeQuery = true)
    void addProfile(String email, String firstName, String lastName, String name, String username );


    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query(value = "UPDATE user_profile SET email =?, username = ? WHERE ID = ?", nativeQuery = true)
    void updateProfile(String email, String username, Long id);
}
