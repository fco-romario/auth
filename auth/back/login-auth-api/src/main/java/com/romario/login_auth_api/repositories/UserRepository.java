package com.romario.login_auth_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.romario.login_auth_api.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
