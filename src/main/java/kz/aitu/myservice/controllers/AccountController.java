package kz.aitu.myservice.controllers;

import kz.aitu.myservice.entities.*;
import kz.aitu.myservice.repository.AccountRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping
    public List<Account> getAll() {
        return AccountRepository.getAll();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable int id) {
        return AccountRepository.getById(id);
    }

    @PostMapping
    public String create(@RequestBody CheckingAccount a) {
        AccountRepository.add(a.getAccountNumber(), a.getBalance(), a.getOverdraftLimit(), a.getCustomerId());
        return "Account added";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody CheckingAccount a) {
        AccountRepository.update(id, a.getAccountNumber(), a.getBalance(), a.getOverdraftLimit());
        return "Account updated";
    }

    @PatchMapping("/{id}")
    public String patch(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        AccountRepository.patch(id, updates);
        return "Account patched";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        AccountRepository.delete(id);
        return "Account deleted";
    }
}