package kz.aitu.myservice.controllers;

import kz.aitu.myservice.entities.Customer;
import kz.aitu.myservice.repository.Dms;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @GetMapping
    public List<Customer> getAll() {
        return Dms.getAllCustomers();
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Customer customer) {
        Dms.addCustomer(customer.getName(), customer.getAddress());
        return ResponseEntity.ok("Customer added");
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable int id) {
        return Dms.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Customer customer) {
        Dms.updateCustomer(id, customer.getName(), customer.getAddress());
        return ResponseEntity.ok("Customer updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Dms.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted");
    }
    @PatchMapping("/{id}")
    public ResponseEntity<String> patch(@PathVariable int id, @RequestBody java.util.Map<String, Object> updates) {

        Customer customer = Dms.getCustomerById(id);
        if (customer == null) return ResponseEntity.notFound().build();


        if (updates.containsKey("name")) {
            customer.setName((String) updates.get("name"));
        }
        if (updates.containsKey("address")) {
            customer.setAddress((String) updates.get("address"));
        }


        Dms.updateCustomer(id, customer.getName(), customer.getAddress());

        return ResponseEntity.ok("Customer patched");
    }
}