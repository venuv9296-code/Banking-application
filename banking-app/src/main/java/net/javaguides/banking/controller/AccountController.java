package net.javaguides.banking.controller;


import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController  // makes spring mvc to class
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    ///  Add accounts
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

//    @GetMapping
//    public List<AccountDto> getAccDetails() {
//        return accountService.getAllAccounts();
//    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllDetails(){
        return new ResponseEntity<>(accountService.getAllAccounts(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto>getByIdAccount(@PathVariable Long id){
        AccountDto account = accountService.getAccountbyId(id);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> updatebyId(@PathVariable Long id, @RequestBody Map<String,Double> request){
            Double amount = request.get("amount");
            AccountDto accountDto = accountService.updatebyId(id, amount);
            return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id, @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdrawAmount(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.ok("Deleted");
    }





}
