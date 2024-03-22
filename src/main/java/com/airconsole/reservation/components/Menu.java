package com.airconsole.reservation.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.airconsole.reservation.interfaces.ICommand;

public class Menu implements ICommand {

	private String _title;
	private String _prompt;

	private Map<Character, MenuItem> _menuItems = new HashMap<Character, MenuItem>();
	private Scanner _scanner = new Scanner(System.in);

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		this._title = title;
	}

	public String getPrompt() {
		return _prompt;
	}

	public void setPrompt(String prompt) {
		this._prompt = prompt;
	}

	public Map<Character, MenuItem> getMenuItems() {
		return _menuItems;
	}

	public void addMenuItem(MenuItem menuItem) {
		this._menuItems.put(Character.toUpperCase(menuItem.getShortcut()), menuItem);
	}

	@Override
	public void execute() {
		boolean userSelectionMade = false;

		if (_title != null) {
			System.out.println(_title);
			System.out.println("");
		}

		for (Map.Entry<Character, MenuItem> entry : _menuItems.entrySet()) {
			System.out.println(entry.getValue());
		}

		System.out.println("");
		System.out.print(getPrompt());

		while (!userSelectionMade) {

			Character userInput = _scanner.next().charAt(0);

			if (getMenuItems().containsKey(Character.toUpperCase(userInput))) {
				userSelectionMade = true;
				getMenuItems().get(Character.toUpperCase(userInput)).execute();
			} else {
				System.err.println("Invalid choice! Please enter a valid selection");
			}
		}
	}

}
