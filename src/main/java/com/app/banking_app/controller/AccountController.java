package com.app.banking_app.controller;


import com.app.banking_app.dto.AccountRecordDTO;
import com.app.banking_app.dto.DepositDTO;
import com.app.banking_app.model.AccountModel;
import com.app.banking_app.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountModel> saveAccount(@RequestBody @Valid AccountRecordDTO accRecDTO){
        AccountModel savedAccount = accountService.saveAccount(accRecDTO);
        return ResponseEntity.ok(savedAccount);
    }

    @PostMapping("/{numberAccount}/deposit")
    public ResponseEntity<AccountModel> deposit(@PathVariable Long numberAccount, @RequestBody DepositDTO depositDTO){
        try{
            AccountModel updateAccount = accountService.deposit(numberAccount,depositDTO.amount());
            return ResponseEntity.ok(updateAccount);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/{numberAccount}/withdraw")
     public ResponseEntity<AccountModel> withdraw(@PathVariable Long numberAccount, @RequestBody DepositDTO depositDTO){
        try {
            AccountModel updateAccount = accountService.withdraw(numberAccount,depositDTO.amount());
            return ResponseEntity.ok(updateAccount);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<AccountModel>> getAllAccount(){
        List<AccountModel> accountModelList = accountService.getAllAccount();
        return ResponseEntity.status(HttpStatus.OK).body(accountModelList);
    }

    @DeleteMapping("/{numberAccount}/delete")
    public ResponseEntity<Object> deleteAcc(@PathVariable("numberAccount") Long numberAccount){
        return accountService.delete(numberAccount);
    }
}
