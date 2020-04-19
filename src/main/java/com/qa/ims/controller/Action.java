package com.qa.ims.controller;

import org.apache.log4j.Logger;

import com.qa.ims.utils.Utils;

/**
 * Action is a collection of commands which are used to determine the type of
 * function to apply to an entity.
 *
 */
public enum Action {
	RETURN("To return to domain selection"), CREATE("To save a new item into the database"),
	READ("To read all items of stated type"), UPDATE("To change an item already in the database"),
	DELETE("To remove an item from the database"),
	SEARCH("Searches for a record in the entity, based on a customer or item name."),
	ORDERITEMS("To remove or add an item to an existing order"), READORDER("To show all items in an existing order"),
	CALCULATE("Calculates the current price on an existing order"),
	READORDER2("returns a better result set, rather than the isntance");

	public static final Logger LOGGER = Logger.getLogger(Action.class);

	/**
	 * Gets an action based on a users input. If user enters a non-specified
	 * enumeration, it will ask for another input.
	 *
	 * @return Action type
	 */
	public static Action setAction(String input) {
		Action action;
		while (true) {
			try {
				action = Action.valueOf(input.toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
				input = getInput();
			}
		}
		return action;
	}

	static String getInput() {
		return Utils.getInput();
	}

	/**
	 * Prints out all posible actions
	 */
	public static void printActions() {
		Action[] action = Action.values();
		for (int i = 0; i < 6; i++) {
			LOGGER.info(action[i].getDescription());
		}
	}

	public static void printOrderActions() {
		Action[] action = Action.values();
		for (int i = 0; i < 8; i++) {
			LOGGER.info(action[i].getDescription());
		}
	}

	private String description;

	private Action() {
	}

	Action(String description) {
		this.description = description;
	}

	/**
	 * Describes the action
	 */
	public String getDescription() {
		return this.name() + ": " + this.description;
	}

}
