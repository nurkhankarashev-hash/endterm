package kz.aitu.myservice.controllers;

import kz.aitu.myservice.entities.Customer;
import kz.aitu.myservice.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @GetMapping
    public List<Customer> getAll() { return CustomerRepository.getAll(); }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable int id) { return CustomerRepository.getById(id); }

    @PostMapping
    public String create(@RequestBody Customer c) {
        CustomerRepository.add(c.getName(), c.getAddress());
        return "Customer added";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Customer c) {
        CustomerRepository.update(id, c.getName(), c.getAddress());
        return "Customer updated";
    }

    @PatchMapping("/{id}")
    public String patch(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        CustomerRepository.patch(id, updates);
        return "Customer patched";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        CustomerRepository.delete(id);
        return "Customer deleted";
    }
}