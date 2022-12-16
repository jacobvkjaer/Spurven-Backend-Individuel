package couchpotatoes.spurven.application.api;



import couchpotatoes.spurven.application.dto.ExpenseRequest;
import couchpotatoes.spurven.application.entity.Expense;
import couchpotatoes.spurven.application.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }
    @GetMapping() //--Get the List of Expense. (READ) (User).
    public List<Expense> getAllExpense(){
        return expenseService.getAllExpense();
    }
    @GetMapping("/{id}") //--get a Expense by by /1 (READ) (User).
    public Expense getExpense(@PathVariable int id){
        return expenseService.getExpense(id);
    }

    @PostMapping("/") //--CREATE an Expense by id (CREATE) (User).
    public Expense createExpense(@RequestBody ExpenseRequest body){
        return expenseService.createExpense(body.getDescription(), body.getCategory(), body.getAmount(),body.getInvoiceStart(), body.getInvoiceEnd());

    }

    @PutMapping("/{id}") //--EDIT an Expense by id (EDIT) (User).
    public Expense editExpense(@RequestBody Expense expense, @PathVariable int id){
        return expenseService.editExpense(expense, id);
    }

    @DeleteMapping("/{id}") //--DELETE an Expense by id (EDIT) (User).
    public String deleteExpense(@PathVariable int id){
        return expenseService.deleteExpense(id);
    }

}
