package be.thibos.personalaccountant.controllers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import be.thibos.personalaccountant.cdi.GsonBuilderDelegate;
import be.thibos.personalaccountant.cdi.qualifiers.InMemoryDAO;
import be.thibos.personalaccountant.model.entities.Expense;
import be.thibos.personalaccountant.model.persistance.ExpenseMemoryDAO;

@ApplicationScoped
public class BalanceController {

	@Inject
	private GsonBuilderDelegate gsonBuilderDelegate;

	@Inject
	@InMemoryDAO
	private ExpenseMemoryDAO expenseDAO;

	@SuppressWarnings("StringBufferMayBeStringBuilder")
	public String printHistory() {
		StringBuffer result = new StringBuffer();

		for (Expense expense : expenseDAO.getAll()) {
			result.append(expense).append("\n");
		}

		return result.toString();
	}

	public void addExpense(Expense expense) {
		expenseDAO.save(expense);
	}

	public void saveToFile(String filePath) {
		List<Expense> expenses = expenseDAO.getAll();

		Gson gson = gsonBuilderDelegate.getGsonBuilder().setPrettyPrinting().create();

		try (FileWriter writer = new FileWriter(filePath)) {
			gson.toJson(expenses, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Expense> readFromFile(String testfilePath) {
		try (FileReader fileReader = new FileReader(testfilePath)) {
			Gson gson = new Gson();

			JsonParser parser = new JsonParser();
			JsonArray object = (JsonArray) parser.parse(fileReader);// response will be the json String

			Type collectionType = new TypeToken<ArrayList<Expense>>() {
			}.getType();
			List<Expense> expenses = gson.fromJson(object, collectionType);

			return expenses;
		} catch (IOException e) {
			System.err.println(e);
			return new ArrayList<>();
		}
	}
}
