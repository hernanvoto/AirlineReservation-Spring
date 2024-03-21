package com.airconsole.reservation.screens;

import com.airconsole.reservation.components.Menu;
import com.airconsole.reservation.interfaces.ICommand;

public abstract class Screen implements ICommand {
	protected String title;
	protected Menu menu;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	protected abstract void drawHeader();
}