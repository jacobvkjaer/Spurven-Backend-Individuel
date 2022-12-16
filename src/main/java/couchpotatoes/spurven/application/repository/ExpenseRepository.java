package couchpotatoes.spurven.application.repository;

import couchpotatoes.spurven.application.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {



}
