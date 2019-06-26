package be.thibos.personalaccountant.model.persistance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import be.thibos.personalaccountant.model.entities.Expense;

@ApplicationScoped
public class ExpenseMemoryDAO implements GenericDAO<Expense> {

	List<Expense> expenses = new ArrayList<>();

	@Override
	public Optional<Expense> get(long id) {
		return Optional.empty();
	}

	@Override
	public List<Expense> getAll() {
		return new ArrayList<>(expenses); // TODO Make unmodifiable?
	}

	@Override
	public void save(Expense expense) {
		expenses.add(expense);
	}

	@Override
	public void update(Expense expense) {

	}

	@Override
	public void delete(Expense expense) {
		expenses.remove(expense);
	}
}
