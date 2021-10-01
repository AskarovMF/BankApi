package ru.askarov.bankapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.askarov.bankapi.model.Card;
import ru.askarov.bankapi.model.TransferBalance;
import ru.askarov.bankapi.service.BankServiceAccount;
import ru.askarov.bankapi.service.BankServiceCard;

import java.math.BigDecimal;
import java.util.Set;

@Slf4j
@RestController("/balance")
public class BalanceController {
    private final BankServiceAccount serviceAccount;
    private final BankServiceCard serviceCard;

    @Autowired
    public BalanceController(BankServiceAccount serviceAccount, BankServiceCard serviceCard) {
        this.serviceAccount = serviceAccount;
        this.serviceCard = serviceCard;
    }

    @PostMapping("/createNewCard/{numberAccount}")
    public ResponseEntity<Card> createNewCard(@PathVariable long numberAccount) {
        Card card = serviceCard.createCard(numberAccount);
        return ResponseEntity.ok(card);
    }

    @GetMapping("/getAllCards/{numberAccount}")
    public ResponseEntity<Set<Card>> getAllCards(@PathVariable long numberAccount) {
        return new ResponseEntity<>(serviceAccount.getAllCards(numberAccount), HttpStatus.OK);
    }

    @PatchMapping("/addBalanceOnAccount")
    public ResponseEntity<TransferBalance> transfer(@RequestBody TransferBalance transferBalance) {
        TransferBalance transfer = serviceAccount.addBalance(transferBalance);
        return ResponseEntity.ok(transfer);
    }

    @GetMapping("/getBalanceOnAccount/{numberAccount}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable long numberAccount) {
        return new ResponseEntity<>(serviceAccount.getBalance(numberAccount), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e) {
        log.error(e.getMessage());
        return e.getMessage();
    }
}
