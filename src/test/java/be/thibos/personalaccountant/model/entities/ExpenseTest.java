package be.thibos.personalaccountant.model.entities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

class ExpenseTest {

	@Test
	void incomes_staticFactoryMethod_onlyTakesValidInput() {
		assertThatIllegalArgumentException().isThrownBy(() -> Expense.of(null, "something", LocalDate.now()));
		assertThatIllegalArgumentException().isThrownBy(() -> Expense.of(BigDecimal.ONE, null, LocalDate.now()));
		assertThatIllegalArgumentException().isThrownBy(() -> Expense.of(BigDecimal.ONE, "", LocalDate.now()));
		assertThatIllegalArgumentException().isThrownBy(() -> Expense.of(BigDecimal.ONE, "something", null));
	}

	@Test
	void incomes_staticFactoryMethod_withValidInputs_doesNotReturnNull() {
		Expense income1 = Expense.of(BigDecimal.ONE, "Work", LocalDate.now());

		assertThat(income1).isNotNull();
	}

	@Test
	void incomesAreEqual_onTimeStampAmountAndSource() {
		Expense income1 = Expense.of(BigDecimal.valueOf(13.37), "Work", LocalDate.of(2019, Month.MAY, 25));
		Expense income2 = Expense.of(BigDecimal.valueOf(13.37), "Work", LocalDate.of(2019, Month.MAY, 25));

		assertThat(income1).isEqualTo(income2);
	}
}
