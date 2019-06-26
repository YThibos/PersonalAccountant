module PersonalAccountant.renameMe {

	opens be.thibos.personalaccountant.model.entities;
	opens be.thibos.personalaccountant.model.persistance;
	opens be.thibos.personalaccountant.controllers;
	opens be.thibos.personalaccountant.cdi;

	requires org.apache.commons.lang3;
	requires cdi.api;
	requires gson;
	requires javax.inject;
	requires java.annotation;

}