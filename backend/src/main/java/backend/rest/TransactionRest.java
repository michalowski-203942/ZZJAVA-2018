package backend.rest;

import backend.dto.TransactionInfo;
import backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Date;

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

    @RequestMapping(value = "/date?{startDate}&{endDate}/all", method = RequestMethod.GET)
    public ResponseEntity getAllTransactionsBetweenDates(
            @PathVariable Date startDate,
            @PathVariable Date endDate){

        return ResponseEntity.ok(service.getAllTransactionsBetweenDates(startDate,endDate));
    }

    @RequestMapping(value = "/date?{startDate}&{endDate}/revenues", method = RequestMethod.GET)
    public ResponseEntity getAllRevenues(
            @PathVariable Date startDate,
            @PathVariable Date endDate){

        return ResponseEntity.ok(service.getAllRevenues(startDate,endDate));
    }

    @RequestMapping(value = "/date?{startDate}&{endDate}/expenses", method = RequestMethod.GET)
    public ResponseEntity getAllExpenses(
            @PathVariable Date startDate,
            @PathVariable Date endDate){

        return ResponseEntity.ok(service.getAllExpenses(startDate,endDate));
    }

    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public ResponseEntity getAllBalance(
            @PathVariable Date startDate,
            @PathVariable Date endDate){

        return ResponseEntity.ok(service.getAllBalance());
    }


    @RequestMapping(value = "/date?{startDate}&{endDate}/balance", method = RequestMethod.GET)
    public ResponseEntity getBalanceBetweenDates(
            @PathVariable Date startDate,
            @PathVariable Date endDate){

        return ResponseEntity.ok(service.getBalanceBetweenDates(startDate,endDate));
    }
}
