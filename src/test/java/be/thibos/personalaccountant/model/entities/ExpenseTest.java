package be.thibos.personalaccountant.model.entities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ExpenseTest {

	@Test
	void expensesAreEqual_whenAmountAndTypeAreEqual() {
		BigDecimal someAmount = BigDecimal.valueOf(13.37);
		Expense expense1 = Expense.of(someAmount);
		Expense expense2 = Expense.of(someAmount);

		assertThat(expense1).isEqualTo(expense2);
	}

	@Test
	void expensesNotEqual_whenAmountIsDifferent() {
		BigDecimal someAmount = BigDecimal.valueOf(13.37);
		BigDecimal anotherAmount = BigDecimal.valueOf(73.31);
		Expense expense1 = Expense.of(someAmount);
		Expense expense2 = Expense.of(anotherAmount);

		assertThat(expense1).isNotEqualTo(expense2);
	}

	@Test
	void expenseOf_nullNegativeOrZero_throwsIllegalArgumentException() {
		assertThatIllegalArgumentException().isThrownBy(() -> Expense.of(null));
		assertThatIllegalArgumentException().isThrownBy(() -> Expense.of(BigDecimal.ZERO));
		assertThatIllegalArgumentException().isThrownBy(() -> Expense.of(BigDecimal.valueOf(-1)));
	}
}
