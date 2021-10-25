package com.erp.backend.repository;

import com.erp.backend.models.AccountVerificationToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<AccountVerificationToken, Long> {
    Optional<AccountVerificationToken> findByToken(String Token);
}
