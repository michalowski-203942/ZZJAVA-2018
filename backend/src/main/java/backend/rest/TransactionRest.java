package backend.rest;

import backend.datastore.entities.Category;
import backend.dto.TransactionInfo;
import backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/api/transaction")
public class TransactionRest {
    @Autowired
    private TransactionService service;

    @RequestMapping(value = "/all",
            method = RequestMethod.GET)
    public ResponseEntity getAllTransactions(HttpServletRequest request){
        return ResponseEntity.ok(service.getAllTransactions(request.getUserPrincipal().getName()));
    }

    @RequestMapping(value = "/add",
            method = RequestMethod.POST)
    public ResponseEntity addTransaction(@RequestBody TransactionInfo transaction){
        return ResponseEntity.ok(service.addTransaction(transaction));
    }

    //pobranie wszystkich przychodow
    @RequestMapping(value = "/incomes", method = RequestMethod.GET)
    public ResponseEntity getAllIncomes()
    {
        return ResponseEntity.ok(service.getAllIncomes());
    }

    //pobranie wszystkich wydatkow
    @RequestMapping(value = "/expenditures", method = RequestMethod.GET)
    public ResponseEntity getAllExpenditures()
    {
        return ResponseEntity.ok(service.getAllExpenditures());
    }

    //pobranie wszystkich przychodow z danej kategorii
    @RequestMapping(value = "/{categoryName}/incomes", method = RequestMethod.GET)
    public ResponseEntity getAllIncomesFromCategory(
            @PathVariable String categoryName){

        return ResponseEntity.ok(service.getAllIncomesFromCategory(categoryName));
    }

    //pobranie wszystkich wydatkow z danej kategorii
    @RequestMapping(value = "/{categoryName}/expenditures", method = RequestMethod.GET)
    public ResponseEntity getAllExpendituresFromCategory(
            @PathVariable String categoryName){

        return ResponseEntity.ok(service.getAllExpendituresFromCategory(categoryName));
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
