package couchpotatoes.spurven.application.service;

import couchpotatoes.spurven.application.entity.Expense;
import couchpotatoes.spurven.application.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    ExpenseRepository expenseRepo;

    @Autowired
    ExpenseService expenseServ;

    @BeforeEach
    public void setup(){
        expenseServ = new ExpenseService(expenseRepo);
    }


    @Test
    void getAllExpense() {
        Mockito.when(expenseRepo.findAll()).thenReturn(List.of(
                new Expense(),
                new Expense()
        ));

        List<Expense> expenses = expenseServ.getAllExpense();

        assertEquals(2,expenses.size());
    }
    /**
     @Test
    void createExpense() {
        Expense expense = new Expense();
        expense.setId(55);

        Mockito.when(expenseRepo.save(any())).thenReturn(expense);

        Expense expense1 = expenseServ.createExpense(expense);

        assertEquals(55, expense1.getId());
    }
*/
    @Test
    void editExpense() {
        Expense expense = new Expense();
        expense.setId(66);
        expense.setCategory("Lorem");

        Mockito.when(expenseRepo.save(any(Expense.class))).thenReturn(expense);
        Mockito.when(expenseRepo.findById(any())).thenReturn(Optional.of(expense));

        Expense editedExpense = expenseServ.editExpense(expense, 66);

        assertEquals(66, editedExpense.getId());
        assertEquals("Lorem", editedExpense.getCategory());
    }

}