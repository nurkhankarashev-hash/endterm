package kz.aitu.myservice.controllers;

import kz.aitu.myservice.entities.Bank;
import kz.aitu.myservice.repository.BankRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/banks")
public class BankController {

    @GetMapping
    public List<Bank> getAll() { return BankRepository.getAll(); }

    @GetMapping("/{id}")
    public Bank getById(@PathVariable int id) { return BankRepository.getById(id); }

    @PostMapping
    public String create(@RequestBody Bank bank) {
        BankRepository.add(bank.getName(), bank.getCountry());
        return "Bank added";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Bank bank) {
        BankRepository.update(id, bank.getName(), bank.getCountry());
        return "Bank updated";
    }

    @PatchMapping("/{id}")
    public String patch(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        BankRepository.patch(id, updates);
        return "Bank patched";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        BankRepository.delete(id);
        return "Bank deleted";
    }
}