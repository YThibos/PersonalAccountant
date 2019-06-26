package be.thibos.personalaccountant.model.persistance;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.inject.Inject;

import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import be.thibos.personalaccountant.cdi.qualifiers.InMemoryDAO;
import be.thibos.personalaccountant.model.entities.Expense;

@ExtendWith(WeldJunit5Extension.class)
class ExpenseMemoryDAOTest {

	@Inject
	@InMemoryDAO
	private ExpenseMemoryDAO expenseMemoryDAO;

	@WeldSetup
	private WeldInitiator weldInitiator = WeldInitiator.of(WeldInitiator.createWeld()
			                                                       .addBeanClasses(ExpenseMemoryDAO.class));

	@BeforeEach
	void setUp() {
		expenseMemoryDAO = new ExpenseMemoryDAO();
	}

	@Test
	void simpleSaveAndGetAll_returnsCollectionContainingSavedObject() {
		Expense anExpense = Expense.of(BigDecimal.ONE, "test", LocalDate.of(2019, Month.JUNE, 26));

		expenseMemoryDAO.save(anExpense);

		List<Expense> allExpenses = expenseMemoryDAO.getAll();

		assertThat(allExpenses).hasSize(1);
		assertThat(allExpenses).contains(anExpense);
	}

	@Test
	void save_getAll_delete_withOneElement_endWithEmptyDAO() {
		Expense anExpense = Expense.of(BigDecimal.ONE, "test", LocalDate.of(2019, Month.JUNE, 26));

		expenseMemoryDAO.save(anExpense);

		List<Expense> allExpenses = expenseMemoryDAO.getAll();
		Expense expense = allExpenses.get(0);

		assertThat(allExpenses).hasSize(1);
		assertThat(allExpenses).contains(anExpense);

		expenseMemoryDAO.delete(expense);

		assertThat(allExpenses).hasSize(1);

		List<Expense> freshExpensesList = expenseMemoryDAO.getAll();

		assertThat(freshExpensesList).isEmpty();
	}
}