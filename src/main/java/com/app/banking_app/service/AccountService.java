package com.app.banking_app.service;

import com.app.banking_app.dto.AccountRecordDTO;
import com.app.banking_app.model.AccountModel;
import com.app.banking_app.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<AccountModel> getAllAccount() {
        return accountRepository.findAll();
    }

    public ResponseEntity<Object> delete(Long numberAccount) {
        Optional<AccountModel> accountE = accountRepository.findById(numberAccount);
        if (accountE.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada!");
        }
        accountRepository.delete(accountE.get());
        return ResponseEntity.status(HttpStatus.OK).body("Conta encerrada com sucesso");
    }

    public AccountModel saveAccount(AccountRecordDTO accRecDTO) {
        AccountModel accModel = new AccountModel();
        BeanUtils.copyProperties(accRecDTO, accModel);
        return accountRepository.save(accModel);
    }

    public AccountModel deposit(Long numberAccount, BigDecimal amount) {
        AccountModel accModel = accountRepository.findById(numberAccount).orElseThrow(() -> new RuntimeException("Conta nao encontrada!"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do deposito deve ser positivo");
        }
        accModel.setBalance(accModel.getBalance().add(amount));

        return accountRepository.save(accModel);
    }

    public AccountModel withdraw(Long numberAccount, BigDecimal amount) {
        AccountModel accModel = accountRepository.findById(numberAccount).orElseThrow(() -> new RuntimeException("Conta nao encontrada!"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do sque deve ser positivo!");
        }
        if (accModel.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Saldo Insuficiente!");
        }

        accModel.setBalance(accModel.getBalance().subtract(amount));

        return accountRepository.save(accModel);
    }

}
