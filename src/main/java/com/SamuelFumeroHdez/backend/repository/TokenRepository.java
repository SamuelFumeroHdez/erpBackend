package com.SamuelFumeroHdez.backend.repository;

import com.SamuelFumeroHdez.backend.models.AccountVerificationToken;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface TokenRepository extends CrudRepository<AccountVerificationToken, Long> {
    Optional<AccountVerificationToken> findByToken(String Token);
}
