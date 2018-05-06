package backend.rest;

import backend.dto.TransactionInfo;
import backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionRest {
    @Autowired
    private TransactionService service;

    @RequestMapping(value = "/all",
            method = RequestMethod.GET)
    public ResponseEntity getAllTransactions(){
        return ResponseEntity.ok(service.getAllTransactions());
    }

    @RequestMapping(value = "/add",
            method = RequestMethod.POST)
    public ResponseEntity addTransaction(@RequestBody TransactionInfo transaction){
        return ResponseEntity.ok(service.addTransaction(transaction));
    }
}
