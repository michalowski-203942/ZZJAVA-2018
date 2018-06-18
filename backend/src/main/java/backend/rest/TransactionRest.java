package backend.rest;

import backend.datastore.entities.Category;
import backend.dto.TransactionInfo;
import backend.exception.AppException;
import backend.exception.IncorrectParamsException;
import backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity addTransaction(@RequestBody TransactionInfo transaction, HttpServletRequest request){
        try {
            return ResponseEntity.ok(service.addTransaction(transaction, request.getUserPrincipal().getName()));
        } catch (IncorrectParamsException | AppException e) {
            return ResponseEntity.status(400).build();
        }
    }

    //pobranie wszystkich przychodow
    @RequestMapping(value = "/incomes", method = RequestMethod.GET)
    public ResponseEntity getAllIncomes(
           HttpServletRequest request)
    {
        return ResponseEntity.ok(service.getAllIncomes(request.getUserPrincipal().getName()));
    }

    //pobranie wszystkich wydatkow
    @RequestMapping(value = "/expenditures", method = RequestMethod.GET)
    public ResponseEntity getAllExpenditures(
            HttpServletRequest request)
    {
        return ResponseEntity.ok(service.getAllExpenditures(request.getUserPrincipal().getName()));
    }

    //pobranie wszystkich przychodow z danej kategorii
    @RequestMapping(value = "/{categoryName}/incomes", method = RequestMethod.GET)
    public ResponseEntity getAllIncomesFromCategory(
            HttpServletRequest request,
            @PathVariable String categoryName){

        return ResponseEntity.ok(service.getAllIncomesFromCategory(request.getUserPrincipal().getName(), categoryName));
    }

    //pobranie wszystkich wydatkow z danej kategorii
    @RequestMapping(value = "/{categoryName}/expenditures", method = RequestMethod.GET)
    public ResponseEntity getAllExpendituresFromCategory(
             HttpServletRequest request,
            @PathVariable String categoryName){

        return ResponseEntity.ok(service.getAllExpendituresFromCategory(request.getUserPrincipal().getName(), categoryName));
    }

    @RequestMapping(value = "/{startDate}/{endDate}/all", method = RequestMethod.GET)
    public ResponseEntity getAllTransactionsBetweenDates(
            HttpServletRequest request,
            @PathVariable ("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @PathVariable ("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate){

        return ResponseEntity.ok(service.getAllTransactionsBetweenDates(request.getUserPrincipal().getName(), startDate,endDate));
    }

    @RequestMapping(value = "/{startDate}/{endDate}/revenues", method = RequestMethod.GET)
    public ResponseEntity getAllRevenues(
            HttpServletRequest request,
            @PathVariable ("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @PathVariable  ("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate){

        return ResponseEntity.ok(service.getAllRevenues(request.getUserPrincipal().getName(), startDate,endDate));
    }

    @RequestMapping(value = "/{startDate}/{endDate}/expenses", method = RequestMethod.GET)
    public ResponseEntity getAllExpenses(
            HttpServletRequest request,
            @PathVariable ("startDate") @DateTimeFormat(pattern="yyyy-MM-dd")Date startDate,
            @PathVariable ("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate){

        return ResponseEntity.ok(service.getAllExpenses(request.getUserPrincipal().getName(), startDate,endDate));
    }

    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public ResponseEntity getAllBalance(
            HttpServletRequest request){

        return ResponseEntity.ok(service.getAllBalance(request.getUserPrincipal().getName()));
    }


    @RequestMapping(value = "/{startDate}/{endDate}/balance", method = RequestMethod.GET)
    public ResponseEntity getBalanceBetweenDates(
            HttpServletRequest request,
            @PathVariable ("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @PathVariable ("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate){

        return ResponseEntity.ok(service.getBalanceBetweenDates(request.getUserPrincipal().getName(), startDate, endDate));
    }


    @RequestMapping(value = "/{transactionId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTransaction (@PathVariable Long transactionId, HttpServletRequest request){
        try {
            service.deleteTransaction(transactionId,request.getUserPrincipal().getName());

        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{transactionId}", method = RequestMethod.GET)
    public ResponseEntity getTransaction(@PathVariable Long transactionId){
        return ResponseEntity.ok(service.getTransactionById(transactionId));
    }
    @RequestMapping(value ="/edit", method = RequestMethod.PUT)
    public ResponseEntity editTransaction(@RequestBody TransactionInfo transaction){
        try {
            service.editTransaction(transaction);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}

