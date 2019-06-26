module PersonalAccountant.renameMe {

	opens be.thibos.personalaccountant.model.entities;
	opens be.thibos.personalaccountant.model.persistance;
	opens be.thibos.personalaccountant.controllers;

	requires org.apache.commons.lang3;
	requires cdi.api;
	requires gson;

}