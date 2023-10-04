package com.userManagement.usermanagementservices.repository;

import com.userManagement.usermanagementservices.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    public Users findByPhoneNo(Long phoneNo);
}
