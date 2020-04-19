package com.qa.ims.persistence.domain;

import org.apache.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum Domain {

	CUSTOMER("Information about customers"), ITEM("Individual Items"), ORDER("Purchases of items"),
	STOP("To close the application");

	public static final Logger LOGGER = Logger.getLogger(Domain.class);

	public static Domain setDomain(String input) {
		Domain domain;
		while (true) {
			try {
				domain = Domain.valueOf(input.toUpperCase());
				break;

			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
				input = getInput();
			}
		}

		return domain;
	}

	static String getInput() {
		return Utils.getInput();
	}

	public static void printDomains() {
		for (Domain domain : Domain.values()) {
			LOGGER.info(domain.getDescription());
		}
	}

	private String description;

	private Domain(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.name() + ": " + this.description;
	}

}
