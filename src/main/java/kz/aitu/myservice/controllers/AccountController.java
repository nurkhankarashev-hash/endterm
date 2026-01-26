package kz.aitu.myservice.controllers;

import kz.aitu.myservice.entities.Account;
import kz.aitu.myservice.entities.CheckingAccount;
import kz.aitu.myservice.repository.Dms;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping
    public List<Account> getAll() {
        return Dms.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable int id) {
        return Dms.getAccountById(id);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody CheckingAccount account) {
        Dms.addAccount(
                account.getAccountNumber(),
                account.getBalance(),
                account.getOverdraftLimit(),
                account.getCustomerId()
        );
        return ResponseEntity.ok("Account added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody CheckingAccount account) {
        Dms.updateAccount(
                id,
                account.getAccountNumber(),
                account.getBalance(),
                account.getOverdraftLimit()
        );
        return ResponseEntity.ok("Account updated");
    }


    @PatchMapping("/{id}")
    public ResponseEntity<String> patch(@PathVariable int id, @RequestBody java.util.Map<String, Object> updates) {

        Account oldAccount = Dms.getAccountById(id);
        if (oldAccount == null) return ResponseEntity.notFound().build();


        CheckingAccount target = (CheckingAccount) oldAccount;


        if (updates.containsKey("accountNumber")) {
            target.setAccountNumber((String) updates.get("accountNumber"));
        }

        if (updates.containsKey("balance")) {

            target.setBalance(((Number) updates.get("balance")).doubleValue());
        }

        if (updates.containsKey("overdraftLimit")) {
            target.setOverdraftLimit(((Number) updates.get("overdraftLimit")).doubleValue());
        }


        Dms.updateAccount(
                id,
                target.getAccountNumber(),
                target.getBalance(),
                target.getOverdraftLimit()
        );

        return ResponseEntity.ok("Account patched via Map");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Dms.deleteAccount(id);
        return ResponseEntity.ok("Account deleted");
    }
}