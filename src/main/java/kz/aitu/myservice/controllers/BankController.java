package kz.aitu.myservice.controllers;

import kz.aitu.myservice.entities.Bank;
import kz.aitu.myservice.repository.Dms;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {

    @GetMapping
    public List<Bank> getAll() {
        return Dms.getAllBanks();
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Bank bank) {
        Dms.addBank(bank.getName(), bank.getCountry());
        return ResponseEntity.ok("Bank added");
    }

    @GetMapping("/{id}")
    public Bank getById(@PathVariable int id) {
        return Dms.getBankById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Bank bank) {
        Dms.updateBank(id, bank.getName(), bank.getCountry());
        return ResponseEntity.ok("Bank updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Dms.deleteBank(id);
        return ResponseEntity.ok("Bank deleted");
    }
    @PatchMapping("/{id}")
    public ResponseEntity<String> patch(@PathVariable int id, @RequestBody java.util.Map<String, Object> updates) {

        Bank bank = Dms.getBankById(id);
        if (bank == null) return ResponseEntity.notFound().build();


        if (updates.containsKey("name")) {
            bank.setName((String) updates.get("name"));
        }
        if (updates.containsKey("country")) {
            bank.setCountry((String) updates.get("country"));
        }


        Dms.updateBank(id, bank.getName(), bank.getCountry());

        return ResponseEntity.ok("Bank patched");
    }
}