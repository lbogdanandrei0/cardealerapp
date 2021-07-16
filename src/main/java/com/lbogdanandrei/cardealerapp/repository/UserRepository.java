package com.lbogdanandrei.cardealerapp.repository;

import com.lbogdanandrei.cardealerapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findUserByEmail(String email);

}
