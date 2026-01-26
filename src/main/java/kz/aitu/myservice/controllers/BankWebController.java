package kz.aitu.myservice.controllers;

import kz.aitu.myservice.repository.Dms;
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

        if (view.equals("accounts")) model.addAttribute("accounts", Dms.getAllAccounts());
        if (view.equals("banks")) model.addAttribute("banks", Dms.getAllBanks());
        if (view.equals("customers")) model.addAttribute("customers", Dms.getAllCustomers());

        return "create";
    }
}