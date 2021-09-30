package ru.askarov.bankapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Card> createNewCard(@PathVariable long numberAccount) {
        Card card = bankService.createCard(numberAccount);
        return ResponseEntity.ok(card);
    }

    @GetMapping("/getAllCards/{numberAccount}")
    public ResponseEntity<Set<Card>> getAllCards(@PathVariable long numberAccount) {
        return new ResponseEntity<>(bankService.getAllCards(numberAccount), HttpStatus.OK);
    }

    @PatchMapping("/addBalanceOnAccount")
    public ResponseEntity<TransferBalance> transfer(@RequestBody TransferBalance transferBalance) {
        TransferBalance transfer = bankService.addBalance(transferBalance);
        return ResponseEntity.ok(transfer);
    }

    @GetMapping("/getBalanceOnAccount/{numberAccount}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable long numberAccount) {
        return new ResponseEntity<>(bankService.getBalance(numberAccount), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e) {
        log.error(e.getMessage());
        return e.getMessage();
    }
}
