package be.thibos.personalaccountant.model.entities;

import java.math.BigDecimal;

public class Expense {
	private BigDecimal amount;

	private Expense(BigDecimal amount) {
		this.amount = amount;
	}

	static Expense of(BigDecimal amount) {
		assertValidAmountInput(amount);
		return new Expense(amount);
	}

	private static void assertValidAmountInput(BigDecimal amount) {
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("An expense amount cannot be null, zero or negative (was " + amount + ")");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Expense expense = (Expense) o;

		return amount.equals(expense.amount);
	}

	@Override
	public int hashCode() {
		return amount.hashCode();
	}
}
