package com.app.banking_app.repository;

import com.app.banking_app.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountModel , Long> {
}
