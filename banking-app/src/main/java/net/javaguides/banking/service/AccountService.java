package net.javaguides.banking.service;

import net.javaguides.banking.dto.AccountDto;

import java.util.List;


public interface AccountService {

    AccountDto createAccount(AccountDto account);

    List<AccountDto> getAllAccounts();

    AccountDto getAccountbyId(Long id);

    void deleteAccountById(Long id);

    AccountDto updatebyId(Long id, double amount);

    AccountDto withdrawAmount(Long id, double amount);
}
