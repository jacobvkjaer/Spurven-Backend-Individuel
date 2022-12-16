package couchpotatoes.spurven.application.service;


import couchpotatoes.spurven.application.entity.Expense;
import couchpotatoes.spurven.application.repository.ContactRepository;
import couchpotatoes.spurven.application.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }
    public List<Expense> getAllExpense() {
        return expenseRepository.findAll();
    }
    public Expense getExpense(int id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Expense not found"));
    }
    public Expense createExpense(String description, String category, double amount, LocalDate invoiceStart, LocalDate invoiceEnd) {
        Expense expense = new Expense(description, category, amount, invoiceStart, invoiceEnd);
        return expenseRepository.save(expense);
    }
    public Expense editExpense(Expense body, int id) {
        Expense expense = getExpense(id);
        expense.setDescription(body.getDescription());
        expense.setCategory(body.getCategory());
        expense.setAmount(body.getAmount());
        return expenseRepository.save(expense);
    }
    public String deleteExpense(int id) {
        Expense expense = getExpense(id);
        expenseRepository.delete(expense);
        return "Expense got deleted";
    }
}
