package com.airconsole.reservation.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.airconsole.reservation.interfaces.ICommand;

@Component
public class Menu implements ICommand {

	private String title;
	private String prompt;
	private Map<Character, MenuItem> menuItems = new HashMap<Character, MenuItem>();
	private Scanner scanner = new Scanner(System.in);

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public Map<Character, MenuItem> getMenuItems() {
		return menuItems;
	}

	public void addMenuItem(MenuItem menuItem) {
		this.menuItems.put(Character.toUpperCase(menuItem.getShortcut()), menuItem);
	}

	@Override
	public void execute() {
		boolean userSelectionMade = false;

		System.out.println(getTitle());
		System.out.println("");

		for (Map.Entry<Character, MenuItem> entry : menuItems.entrySet()) {
			Character key = entry.getKey();
			MenuItem menuItem = entry.getValue();
			System.out.println(menuItem);
		}

		System.out.println("");
		System.out.print(getPrompt());

		while (!userSelectionMade) {

			Character userInput = scanner.next().charAt(0);

			if (getMenuItems().containsKey(Character.toUpperCase(userInput))) {
				userSelectionMade = true;
				getMenuItems().get(Character.toUpperCase(userInput)).execute();
			} else {
				System.err.println("Invalid choice! Please enter a valid selection");
			}
		}
	}

}
