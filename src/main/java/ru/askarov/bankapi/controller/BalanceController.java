package ru.askarov.bankapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.askarov.bankapi.model.TransferBalance;
import ru.askarov.bankapi.service.BankService;

import java.math.BigDecimal;

@Controller
@Slf4j
@RestController("/balance")
public class BalanceController {
    private final BankService bankService;

    @Autowired
    public BalanceController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/{accountId}")
    public BigDecimal getBalance(@PathVariable long accountId) {
        return bankService.getBalance(accountId);
    }

    @PostMapping("/add")
    public BigDecimal addMoney(@RequestBody TransferBalance transferBalance) {
        return bankService.addMoney(transferBalance.getTo(), transferBalance.getAmount());
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferBalance transferBalance) {
        bankService.makeTransfer(transferBalance);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e){
        log.error(e.getMessage());
        return "Not found";
    }
}
