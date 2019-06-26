package be.thibos.personalaccountant.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.inject.Inject;

import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import be.thibos.personalaccountant.cdi.GsonBuilderDelegate;
import be.thibos.personalaccountant.model.entities.Expense;
import be.thibos.personalaccountant.model.persistance.ExpenseMemoryDAO;

@ExtendWith(WeldJunit5Extension.class)
class BalanceControllerTest {

	private static final String TESTFILE_PATH = "C:/Users/Thibos/Desktop/testfile.json";

	@WeldSetup
	private WeldInitiator weldInitiator = WeldInitiator.of(WeldInitiator.createWeld()
			                                                       .addBeanClasses(BalanceController.class,
			                                                                       ExpenseMemoryDAO.class,
			                                                                       GsonBuilderDelegate.class));

	@Inject
	private BalanceController balanceController;

	@Test
	void addingAndPrintingMultipleExpenses_notSoPrettyFormat() {
		balanceController.addExpense(Expense.of(BigDecimal.valueOf(100), "Test1", LocalDate.of(2019, Month.JUNE, 26)));
		balanceController.addExpense(Expense.of(BigDecimal.valueOf(200), "Test2", LocalDate.of(2019, Month.JUNE, 25)));
		balanceController.addExpense(Expense.of(BigDecimal.valueOf(300), "Test3", LocalDate.of(2019, Month.JUNE, 24)));
		balanceController.addExpense(Expense.of(BigDecimal.valueOf(400), "Test4", LocalDate.of(2019, Month.JUNE, 23)));

		assertThat(balanceController.printHistory()).isEqualTo("Expense{amount=100, source='Test1', date=2019-06-26}\n" +
		                                                       "Expense{amount=200, source='Test2', date=2019-06-25}\n" +
		                                                       "Expense{amount=300, source='Test3', date=2019-06-24}\n" +
		                                                       "Expense{amount=400, source='Test4', date=2019-06-23}\n");
	}

	@Test
	void addingAndSavingToFile_savesExpensesInJsonFormat_andReadsObjectsBackProperly() throws IOException {
		Expense expense1 = Expense.of(BigDecimal.valueOf(100), "Test1", LocalDate.of(2019, Month.JUNE, 26));
		Expense expense2 = Expense.of(BigDecimal.valueOf(200), "Test2", LocalDate.of(2019, Month.JUNE, 25));

		balanceController.addExpense(expense1);
		balanceController.addExpense(expense2);

		balanceController.saveToFile(TESTFILE_PATH);

		List<Expense> expenses = balanceController.readFromFile(TESTFILE_PATH);

		assertThat(expenses).hasSize(2);
		assertThat(expenses).contains(expense1);
		assertThat(expenses).contains(expense2);
	}
}