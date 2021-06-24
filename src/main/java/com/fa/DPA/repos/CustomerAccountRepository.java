package com.fa.DPA.repos;

import com.fa.DPA.model.CustomerAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends BaseRepository<CustomerAccount>{

    @Override
    Optional<CustomerAccount> findById(Long aLong);

    @Override
    Page findAll(Pageable pageable);


    @Query("select c from CustomerAccount c where c.email = :email")
    Optional<CustomerAccount> findByEmail(String email);

    @Query("select c from CustomerAccount  c inner join PasswordResetToken p on c.id = p.user.id " +
            "where p.token = :token and p.expiryTime >= :timeNow")
    Optional<CustomerAccount> findByResetPasswordToken(String token, LocalDateTime timeNow);


}
