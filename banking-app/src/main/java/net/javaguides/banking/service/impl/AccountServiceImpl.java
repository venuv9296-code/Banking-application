package net.javaguides.banking.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.mapper.AccountMapper;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }



    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(AccountMapper::mapToAccountDto)
                .toList();
    }

    //@Override
    public AccountDto getAccountbyId(Long id){
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Acc doesn't exists")) ;
        return AccountMapper.mapToAccountDto(account);
    }


    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    public AccountDto updatebyId(Long id, double amount){
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Acc doesn't exists"));
        double total = account.getBalance()+amount;
        account.setBalance(total);
        Account savedDeposit = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedDeposit);
    }

    @Override
public AccountDto withdrawAmount(Long id, double amount){
    Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Acc doesn't exists"));
    if(account.getBalance()<amount){
        throw new RuntimeException("Insufficient fund");
    }
    double total = account.getBalance() - amount;
    account.setBalance(total);
    Account savedDeposit = accountRepository.save(account);
    return AccountMapper.mapToAccountDto(savedDeposit);
}
}

