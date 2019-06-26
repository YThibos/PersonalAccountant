package be.thibos.personalaccountant.model.entities;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {

	private final BigDecimal amount;
	private final String source;
	private final LocalDate date;

	private Expense(BigDecimal amount, String source, LocalDate date) {
		this.amount = validateAmount(amount);
		this.source = validateSource(source);
		this.date = validateDate(date);
	}

	public static Expense of(BigDecimal amount, String source, LocalDate date) {
		return new Expense(amount, source, date);
	}

	private BigDecimal validateAmount(BigDecimal amount) {
		if (amount != null) {
			return amount;
		} else {
			throw new IllegalArgumentException("An amount cannot be null for the creation of an Expense.");
		}
	}

	private String validateSource(String source) {
		if (isNotBlank(source)) {
			return source;
		} else {
			throw new IllegalArgumentException("A source cannot be null for the creation of an Expense.");
		}
	}

	private LocalDate validateDate(LocalDate date) {
		if (date != null) {
			return date;
		} else {
			throw new IllegalArgumentException("A date cannot be null for the creation of an Expense.");
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

		Expense income = (Expense) o;

		if (!amount.equals(income.amount)) {
			return false;
		}
		if (!source.equals(income.source)) {
			return false;
		}
		return date.equals(income.date);
	}

	@Override
	public int hashCode() {
		int result = amount.hashCode();
		result = 31 * result + source.hashCode();
		result = 31 * result + date.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Expense{" +
		       "amount=" + amount +
		       ", source='" + source + '\'' +
		       ", date=" + date +
		       '}';
	}
}
