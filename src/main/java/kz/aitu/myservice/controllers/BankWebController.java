package kz.aitu.myservice.controllers;

// Жаңа репозиторийлерді импорттау міндетті
import kz.aitu.myservice.repository.AccountRepository;
import kz.aitu.myservice.repository.BankRepository;
import kz.aitu.myservice.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankWebController {

    @GetMapping("/")
    public String home() {
        return "redirect:/service?view=home";
    }

    @GetMapping("/service")
    public String showService(@RequestParam(value = "view", defaultValue = "home") String view, Model model) {
        model.addAttribute("view", view);


        if (view.equals("accounts")) {
            model.addAttribute("accounts", AccountRepository.getAll());
        } else if (view.equals("banks")) {
            model.addAttribute("banks", BankRepository.getAll());
        } else if (view.equals("customers")) {
            model.addAttribute("customers", CustomerRepository.getAll());
        }


        return "create";
    }
}