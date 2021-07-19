package com.lbogdanandrei.cardealerapp.repository;

import com.lbogdanandrei.cardealerapp.model.TokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenModel, Integer> {

    Optional<TokenModel> findTokenByToken(String token);

    @Query("from token where email = :myEmail")
    Optional<TokenModel> findTokenByEmail(@Param("myEmail") String email);
}
