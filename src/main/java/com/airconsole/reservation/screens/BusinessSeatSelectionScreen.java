package com.airconsole.reservation.screens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.airconsole.reservation.components.Menu;
import com.airconsole.reservation.model.Airplane;

@Service
@Scope("singleton")
public class BusinessSeatSelectionScreen extends SeatSelectionScreen {

	public static final Character BUSINESS_CLASS_SELECTION_ID = 'B';
	public static final Character EXIT_SELECTION_ID = 'X';

	@Autowired
	private final Airplane _airplane;

	public BusinessSeatSelectionScreen(final Airplane airplane) {
		_airplane = airplane;

		setTitle("Business Class");

		_airplane.printReservationMap(Airplane.PlaneClass.BUSINESS_CLASS);

		Menu menu = new Menu();

		setMenu(menu);
	}

	@Override
	public void execute() {

		drawHeader();

		if (menu != null)
			menu.execute();
		else
			System.err.println("BusinessSeatSelectionScreen menu is NULL");
	}

	@Override
	protected void drawHeader() {
		System.out.println("*******************************");
		System.out.println("***** " + getTitle() + "  ****");
		System.out.println("*******************************");
	}

}
