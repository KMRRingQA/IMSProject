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
	READ("To read an item from the database"), UPDATE("To change an item already in the database"),
	DELETE("To remove an item from the database"), ADDITEM("To add an item to an existing order"),
	DELETEITEM("To delete an item from an existing order"), READITEMS("To show all items in an existing order"),
	CALCULATEORDERPRICE("Calculates the current price on an existing order");

	public static final Logger LOGGER = Logger.getLogger(Action.class);

	/**
	 * Gets an action based on a users input. If user enters a non-specified
	 * enumeration, it will ask for another input.
	 *
	 * @return Action type
	 */
	public static Action getAction() {
		Action action;
		while (true) {
			try {
				action = Action.valueOf(Utils.getInput().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return action;
	}

	/**
	 * Prints out all posible actions
	 */
	public static void printActions() {
		Action[] action = Action.values();
		for (int i = 0; i < 5; i++) {
			LOGGER.info(action[i].getDescription());
		}
	}

	public static void printOrderActions() {
		for (Action action : Action.values()) {
			LOGGER.info(action.getDescription());
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
