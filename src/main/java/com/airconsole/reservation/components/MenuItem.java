package com.airconsole.reservation.components;

import com.airconsole.reservation.actions.MenuAction;
import com.airconsole.reservation.interfaces.ICommand;

public class MenuItem implements ICommand {
	private String name;
	private Character shortcut;
	private MenuAction action;

	public MenuItem(Character shortcut, String name, MenuAction action) {
		setName(name);
		setShortcut(shortcut);
		setMenuAction(action);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getShortcut() {
		return shortcut;
	}

	public void setShortcut(Character shortcut) {
		this.shortcut = shortcut;
	}

	public void setMenuAction(MenuAction action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return this.getShortcut() + ": " + this.getName();
	}

	@Override
	public void execute() {
		this.action.perform();
	}
}
