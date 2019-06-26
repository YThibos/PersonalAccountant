package be.thibos.personalaccountant.cdi;

import javax.annotation.PostConstruct;

import com.google.gson.GsonBuilder;

public class GsonBuilderDelegate {

	private GsonBuilder gsonBuilder = new GsonBuilder();

	private GsonBuilderDelegate() {

	}

	@PostConstruct
	private void postConstruct() {
		gsonBuilder.setPrettyPrinting();
	}

	public GsonBuilder getGsonBuilder() {
		return gsonBuilder;
	}
}