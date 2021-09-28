package ru.askarov.bankapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.askarov.bankapi.model.Card;
import ru.askarov.bankapi.model.TransferBalance;
import ru.askarov.bankapi.service.BankService;

import java.math.BigDecimal;
import java.util.Set;

@Controller
@Slf4j
@RestController("/balance")
public class BalanceController {
    private final BankService bankService;

    @Autowired
    public BalanceController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/createNewCard/{numberAccount}")
    public void createNewCard(@PathVariable long numberAccount) {
        bankService.createCard(numberAccount);
    }

    @GetMapping("/getAllCards/{numberAccount}")
    public Set<Card> getAllCards(@PathVariable long numberAccount){
        return bankService.getAllCards(numberAccount);
    }

    @PatchMapping("/addBalanceOnAccount")
    public void transfer(@RequestBody TransferBalance transferBalance) {
        bankService.addBalance(transferBalance);
    }

    @GetMapping("/getBalanceOnAccount/{numberAccount}")
    public BigDecimal getBalance(@PathVariable long numberAccount) {
        return bankService.getBalance(numberAccount);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e){
        log.error(e.getMessage());
        return e.getMessage();
    }
}
